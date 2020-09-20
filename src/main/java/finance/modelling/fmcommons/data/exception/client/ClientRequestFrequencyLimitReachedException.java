package finance.modelling.fmcommons.data.exception.client;

public class ClientRequestFrequencyLimitReachedException extends Exception {
    public ClientRequestFrequencyLimitReachedException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
