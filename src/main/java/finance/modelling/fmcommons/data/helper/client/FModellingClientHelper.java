package finance.modelling.fmcommons.data.helper.client;

import finance.modelling.fmcommons.data.exception.client.ClientDailyRequestLimitReachedException;
import finance.modelling.fmcommons.data.exception.client.ClientEndPointHasNoDataException;
import finance.modelling.fmcommons.data.exception.client.ClientRequestFrequencyLimitReachedException;
import finance.modelling.fmcommons.data.exception.client.InvalidApiKeyException;
import finance.modelling.fmcommons.data.logging.LogClient;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static finance.modelling.fmcommons.data.exception.ExceptionParser.isKafkaException;
import static finance.modelling.fmcommons.data.exception.ExceptionParser.isSaslAuthentificationException;
import static finance.modelling.fmcommons.data.logging.LogClient.getSimpleNameAndMessage;
import static reactor.core.Exceptions.isRetryExhausted;

@NoArgsConstructor
public class FModellingClientHelper {

    public Throwable returnTechnicalException(Throwable error) {
        Throwable customException;
        if (is401InvalidAuthorisationResponse(error)) {
            customException = new InvalidApiKeyException("Invalid Api Key. Have you copied is correctly?", error);
        }
        else if (is429TooManyRequests(error)) {
            customException = new ClientRequestFrequencyLimitReachedException(
                    "Request frequency exceeded. Limit rate further.", error);
        }
        else if (isEmptyPayloadReceived(error)) {
            customException = new ClientEndPointHasNoDataException(
                    "Client has no data to receive from this endpoint. It's likely it doesn't exist.", error);
        }
        else {
            customException = error;
        }
        return customException;
    }

    protected boolean is401InvalidAuthorisationResponse(Throwable error) {
        return error.getMessage().contains("401 Unauthorized from GET");
    }

    protected boolean is429TooManyRequests(Throwable error) {
        return error.getMessage().contains("429 Too Many Requests from GET");
    }

    protected boolean isEmptyPayloadReceived(Throwable error) {
        return error.getMessage().contains("Index 0 out of bounds for length 0");
    }

    public boolean isRetryableException(Throwable error) {
        boolean isRetryable = true;
        if (
                error.getClass().equals(InvalidApiKeyException.class) ||
                error.getClass().equals(ClientEndPointHasNoDataException.class) ||
                error.getClass().equals(ClientDailyRequestLimitReachedException.class)
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
