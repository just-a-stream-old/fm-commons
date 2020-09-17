package finance.modelling.fmcommons.exception;

import finance.modelling.fmcommons.exception.client.ClientDailyRequestLimitReached;
import org.apache.kafka.common.KafkaException;

public class ParseException {

    public static boolean isClientDailyRequestLimitReached(Throwable error) {
        return error.getClass().equals(ClientDailyRequestLimitReached.class);
    }

    public static boolean isKafkaException(Throwable error) {
        return error.getClass().equals(KafkaException.class);
    }
}
