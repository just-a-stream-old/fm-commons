package finance.modelling.fmcommons.data.helper.client;

import finance.modelling.fmcommons.data.exception.client.InvalidApiKeyException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FModellingClientHelper {

    public Throwable returnTechnicalException(Throwable error) {
        Throwable customException;
        if (is401InvalidAuthorisationResponse(error)) {
            customException = new InvalidApiKeyException("Invalid Api Key. Have you copied is correctly?", error);
        }
        else {
            customException = error;
        }
        return customException;
    }

    protected boolean is401InvalidAuthorisationResponse(Throwable error) {
        return error.getMessage().contains("401 Unauthorized from GET");
    }

    public boolean isNotRetryableException(Throwable error) {
        boolean isNotRetryable = false;
        if (error.getClass().equals(InvalidApiKeyException.class)) {
            isNotRetryable = true;
        }
        return isNotRetryable;
    }
}
