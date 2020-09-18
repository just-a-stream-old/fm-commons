package finance.modelling.fmcommons.exception.client;

public class InvalidApiKeyException extends Exception {
    public InvalidApiKeyException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
