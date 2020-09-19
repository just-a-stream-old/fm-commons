package finance.modelling.fmcommons.data.helper.api.publisher;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

public class PublisherHelper {

    public static Message<Object> buildMessageWithTraceIdHeader(Object payload, UUID traceId) {
        return MessageBuilder
                .withPayload(payload)
                .setHeader("x-trace-id", traceId)
                .build();
    }

    public static ProducerRecord<String, Object> buildProducerRecordWithTraceIdHeader(
            String topic,
            String key,
            Object value,
            String traceId) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, key, value);
        record.headers().add(new RecordHeader("x-trace-id", traceId.getBytes()));
        return record;
    }
}
