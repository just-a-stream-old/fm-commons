package finance.modelling.fmcommons.data.exception;

import finance.modelling.fmcommons.data.exception.client.ClientDailyRequestLimitReachedException;
import finance.modelling.fmcommons.data.exception.client.ClientEndPointHasNoDataException;
import finance.modelling.fmcommons.data.exception.client.ClientRequestFrequencyLimitReachedException;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.SaslAuthenticationException;

public class ExceptionParser {

    public static boolean isClientDailyRequestLimitReached(Throwable error) {
        return error.getClass().equals(ClientDailyRequestLimitReachedException.class);
    }

    public static boolean isKafkaException(Throwable error) {
        return error.getClass().equals(KafkaException.class);
    }

    public static boolean isSaslAuthentificationException(Throwable error) {
        return error.getClass().equals(SaslAuthenticationException.class);
    }

    public static boolean isClientEndPointHasNoDataException(Throwable error) {
        return error.getClass().equals(ClientEndPointHasNoDataException.class);
    }

    public static boolean isClientRequestFrequencyLimitReachedException(Throwable error) {
        return error.getClass().equals(ClientRequestFrequencyLimitReachedException.class);
    }

}
