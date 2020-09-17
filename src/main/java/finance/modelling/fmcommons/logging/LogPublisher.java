package finance.modelling.fmcommons.logging;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Slf4j
public class LogPublisher {

    private static final Gson gson = new Gson();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss'Z'"));

    public static void logInfoDataItemSent(
            Class<?> dataItem,
            String outputTopic,
            List<UUID> traceIds,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemSentMap(dataItem, outputTopic, traceIds);
        orderedMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogInfoDataItemSentMap(
            Class<?> dataItem,
            String outputTopic,
            List<UUID> traceIds) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Sent ProducerRecord with value type: %s to Kafka", dataItem.getSimpleName())));
        baseOrderedLogMap.put("topic", outputTopic);
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("traceId(s)", traceIds);
        return baseOrderedLogMap;
    }

    public static void logInfoDataItemSent(
            Class<?> dataItem,
            String outputTopic,
            List<UUID> traceIds) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemSentMap(dataItem, outputTopic, traceIds);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }
}
