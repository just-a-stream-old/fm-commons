package finance.modelling.fmcommons.exception;

import finance.modelling.fmcommons.exception.client.ClientDailyRequestLimitReachedException;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.SaslAuthenticationException;

public class ParseException {

    public static boolean isClientDailyRequestLimitReached(Throwable error) {
        return error.getClass().equals(ClientDailyRequestLimitReachedException.class);
    }

    public static boolean isKafkaException(Throwable error) {
        return error.getClass().equals(KafkaException.class);
    }

    public static boolean isSaslAuthentificationException(Throwable error) {
        return error.getClass().equals(SaslAuthenticationException.class);
    }
}
