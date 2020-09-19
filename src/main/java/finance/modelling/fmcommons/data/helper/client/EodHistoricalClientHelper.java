package finance.modelling.fmcommons.data.helper.client;

import finance.modelling.fmcommons.data.exception.client.ClientDailyRequestLimitReachedException;
import finance.modelling.fmcommons.data.exception.client.InvalidApiKeyException;
import lombok.NoArgsConstructor;

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

    public boolean isNotRetryableException(Throwable error) {
        boolean isNotRetryable = false;
        if (
                error.getClass().equals(ClientDailyRequestLimitReachedException.class) ||
                        error.getClass().equals(InvalidApiKeyException.class)
        ) {
            isNotRetryable = true;
        }
        return isNotRetryable;
    }
}
