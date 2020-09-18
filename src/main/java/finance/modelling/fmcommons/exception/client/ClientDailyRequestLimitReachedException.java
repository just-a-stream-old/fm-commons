package finance.modelling.fmcommons.exception.client;

public class ClientDailyRequestLimitReachedException extends Exception {
    public ClientDailyRequestLimitReachedException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
