package finance.modelling.fmcommons.data.exception.client;

public class ClientEndPointHasNoDataException extends Exception {
    public ClientEndPointHasNoDataException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
