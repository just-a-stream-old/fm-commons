package finance.modelling.fmcommons.data.helper.client;

import finance.modelling.fmcommons.data.exception.client.ClientDailyRequestLimitReachedException;
import finance.modelling.fmcommons.data.exception.client.InvalidApiKeyException;
import finance.modelling.fmcommons.data.logging.LogClient;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static finance.modelling.fmcommons.data.exception.ExceptionParser.*;
import static finance.modelling.fmcommons.data.logging.LogClient.getSimpleNameAndMessage;
import static reactor.core.Exceptions.isRetryExhausted;

@NoArgsConstructor
public class EodHistoricalClientHelper {

    public Throwable returnTechnicalException(Throwable error) {
        Throwable customException;
        if (is402PaymentRequiredResponse(error)) {
            customException = new ClientDailyRequestLimitReachedException("100k Requests", error);
        }
        else if (is401InvalidAuthorisationResponse(error)) {
            customException = new InvalidApiKeyException("Invalid Api Key. Have you copied is correctly?", error);
        }
        else {
            customException = error;
        }
        return customException;
    }

    protected boolean is402PaymentRequiredResponse(Throwable error) {
        return error.getMessage().contains("402 Payment Required from GET");
    }

    protected boolean is401InvalidAuthorisationResponse(Throwable error) {
        return error.getMessage().contains("401 Unauthorized from GET");
    }

    public boolean isRetryableException(Throwable error) {
        boolean isRetryable = true;
        if (
                error.getClass().equals(ClientDailyRequestLimitReachedException.class) ||
                error.getClass().equals(InvalidApiKeyException.class)
        ) {
            isRetryable = false;
        }
        return isRetryable;
    }

    public void respondToErrorType(String identifier, Class<?> clazz, Throwable error, String logResourcePath) {
        List<String> responseToError = new LinkedList<>();
        Map<String, Object> additionalInfo = new HashMap<>();

        if (isKafkaException(error) || isSaslAuthentificationException(error)) {
            responseToError.add("Log stacktrace");
            error.printStackTrace();
        }
        else if (isClientDailyRequestLimitReached(error)) {
            // Todo: Schedule stateful retry system - schedule retry at midnight to bypass daily limit
            responseToError.add("Schedule retry...");
        }
        else if (isRetryExhausted(error)) {
            responseToError.add("Log original exception & retry info");
            additionalInfo.put("retry info", getSimpleNameAndMessage(error));
            error = error.getCause();
        }
        else {
            responseToError.add("Default logging");
        }
        LogClient.logErrorFailedToReceiveDataItem(
                identifier, clazz, error, logResourcePath, responseToError, additionalInfo);
    }
}
