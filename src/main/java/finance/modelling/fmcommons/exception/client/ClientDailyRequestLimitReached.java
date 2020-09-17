package finance.modelling.fmcommons.exception.client;

public class ClientDailyRequestLimitReached extends Exception {
    public ClientDailyRequestLimitReached(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
