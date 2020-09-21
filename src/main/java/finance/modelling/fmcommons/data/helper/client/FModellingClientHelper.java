package finance.modelling.fmcommons.data.helper.client;

import finance.modelling.fmcommons.data.exception.client.ClientEndPointHasNoDataException;
import finance.modelling.fmcommons.data.exception.client.ClientRequestFrequencyLimitReachedException;
import finance.modelling.fmcommons.data.exception.client.InvalidApiKeyException;
import finance.modelling.fmcommons.data.logging.LogClient;
import io.netty.handler.ssl.SslHandshakeTimeoutException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.PrematureCloseException;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static finance.modelling.fmcommons.data.exception.ExceptionParser.*;
import static finance.modelling.fmcommons.data.logging.LogClient.getSimpleNameAndMessage;
import static reactor.core.Exceptions.isRetryExhausted;

@NoArgsConstructor
@Slf4j
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
                    "Client has no data to receive from this endpoint. It is likely it does not exist.", error);
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
        boolean isRetryable = false;
        if (
                error.getClass().equals(ClientRequestFrequencyLimitReachedException.class) ||
                error.getClass().equals(SslHandshakeTimeoutException.class) ||
                error.getClass().equals(PrematureCloseException.class) ||
                error.getClass().equals(SSLException.class) ||
                error.getClass().equals(IOException.class)

        ) {
            isRetryable = true;
        }
        return isRetryable;
    }

    public void respondToErrorType(String identifier, Class<?> clazz, Throwable error, String logResourcePath) {
        List<String> responseToError = new LinkedList<>();
        Map<String, Object> additionalInfo = new HashMap<>();

        if (isClientEndPointHasNoDataException(error)) {
            responseToError.add("Log debug and ignore.");
            LogClient.logDebugFailedToReceiveDataItem(identifier, clazz, error, logResourcePath, responseToError, additionalInfo);
        }
        else {
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
            LogClient.logErrorFailedToReceiveDataItem(identifier, clazz, error, logResourcePath, responseToError, additionalInfo);
        }
    }
}
