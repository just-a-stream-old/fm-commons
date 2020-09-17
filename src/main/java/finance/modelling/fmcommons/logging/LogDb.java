package finance.modelling.fmcommons.logging;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class LogDb {

    private static final Gson gson = new Gson();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss'Z'"));

    public static void logDebugDataItemIngested(
            Class<?> dataItem,
            String identifier,
            String resourceUri) {
        Map<String, Object> orderedLogMap = buildBaseLogDebugDataItemIngestedMap(dataItem, identifier, resourceUri);
        log.info(gson.toJson(orderedLogMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogDebugDataItemIngestedMap(
            Class<?> dataItem,
            String identifier,
            String resourceUri) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Ingested data type: %s with identifier: %s", dataItem.getSimpleName(), identifier)));
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("source(s)", resourceUri);
        return baseOrderedLogMap;
    }

    public static void logDebugDataItemIngested(
            Class<?> dataItem,
            String identifier,
            String resourceUri,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedLogMap = buildBaseLogDebugDataItemIngestedMap(dataItem, identifier, resourceUri);
        orderedLogMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedLogMap, LinkedHashMap.class));
    }
}
