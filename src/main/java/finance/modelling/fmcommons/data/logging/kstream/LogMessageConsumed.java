package finance.modelling.fmcommons.data.logging.kstream;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static finance.modelling.fmcommons.data.logging.LogConsumer.determineTraceIdFromHeaders;

@Slf4j
public class LogMessageConsumed<V> implements ValueTransformer<V, V> {

    private final String traceIdHeaderName;
    private ProcessorContext context;

    private static final Gson gson = new Gson();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss'Z'"));

    public LogMessageConsumed(String traceIdHeaderName) {
        this.traceIdHeaderName = traceIdHeaderName;
    }

    public void init(ProcessorContext context) {
        this.context = context;
    }

    public V transform(V value) {
        try {
            Map<String, Object> orderedLogMap = new LinkedHashMap<>();
            orderedLogMap.put("event", (String.format("Consumed KStream<String, %s> message from Kafka", value.getClass().getSimpleName())));
            orderedLogMap.put("topic", context.topic());
            orderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
            orderedLogMap.put("traceId", determineTraceIdFromHeaders(context.headers(), traceIdHeaderName));
            log.info(gson.toJson(orderedLogMap, LinkedHashMap.class));
        }
        catch (Exception e) {
            Map<String, Object> orderedLogErrorMap = new LinkedHashMap<>();
            orderedLogErrorMap.put("event", (String.format("Failure to log Kafka message of type: %s", value.getClass().getSimpleName())));
            orderedLogErrorMap.put("topic", context.topic());
            orderedLogErrorMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
            orderedLogErrorMap.put("traceId", "Unknown");
            orderedLogErrorMap.put("additionalInfo", Map.of("headers", context.headers().toString(), "offset", context.offset()));
            log.error(gson.toJson(orderedLogErrorMap, LinkedHashMap.class));
        }
        return value;
    }

    public void close() {

    }
}
