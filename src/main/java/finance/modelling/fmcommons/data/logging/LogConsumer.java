package finance.modelling.fmcommons.data.logging;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Slf4j
public class LogConsumer {

    private static final Gson gson = new Gson();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss'Z'"));

    public static void logInfoDataItemConsumed(
            Class<?> dataItem,
            String inputTopic,
            String traceId,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemConsumedMap(dataItem, inputTopic, traceId);
        orderedMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogInfoDataItemConsumedMap(
            Class<?> dataItem,
            String inputTopic,
            String traceId) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Consumed ConsumerRecord with value type: %s from Kafka", dataItem.getSimpleName())));
        baseOrderedLogMap.put("topic", inputTopic);
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("traceId", traceId);
        return baseOrderedLogMap;
    }

    public static void logInfoDataItemConsumed(
            Class<?> dataItem,
            String inputTopic,
            String traceId) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemConsumedMap(dataItem, inputTopic, traceId);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    public static void logErrorFailedToConsumeDataItem(
            Class<?> dataItem,
            String inputTopic,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogErrorFailedToConsumeDataItemMap(dataItem, inputTopic);
        orderedMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogErrorFailedToConsumeDataItemMap(
            Class<?> dataItem,
            String inputTopic) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Failure to consume ConsumerRecord with value type: %s from Kafka", dataItem.getSimpleName())));
        baseOrderedLogMap.put("topic", inputTopic);
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        return baseOrderedLogMap;
    }

    public static void logErrorFailedToConsumeDataItem(
            Class<?> dataItem,
            String inputTopic) {
        Map<String, Object> orderedMap = buildBaseLogErrorFailedToConsumeDataItemMap(dataItem, inputTopic);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    public static String determineTraceIdFromHeaders(Headers headers) {
        String traceId;
        byte[] traceIdBytes = null;
        for (Header header : headers) {
            if (header.key().equals("x-trace-id")) {
                traceIdBytes = header.value();
            }
        }
        if (traceIdBytes == null) {
            // Todo: Log or throw an exception!
            traceId = "Unknown";
        }
        else {
            traceId = new String(traceIdBytes);
        }
        return traceId;
    }
}
