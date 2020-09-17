package finance.modelling.fmcommons.helper.api.publisher;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

public class PublisherHelper {

    public static Message<Object> buildMessageWithTraceIdHeader(Object payload, UUID traceId) {
        return MessageBuilder
                .withPayload(payload)
                .setHeader("traceId", traceId)
                .build();
    }
}
