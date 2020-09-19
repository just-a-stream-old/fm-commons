package finance.modelling.fmcommons.data.exception.client;

public class ClientDailyRequestLimitReachedException extends Exception {
    public ClientDailyRequestLimitReachedException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
