package finance.modelling.fmcommons.data.logging;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class LogDb {

    private static final Gson gson = new Gson();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss'Z'"));

    public static void logDebugDataItemQueried(
            Class<?> dataItem,
            String identifier,
            String resourcePath) {
        Map<String, Object> orderedLogMap = buildBaseLogDebugDataItemQueriedMap(dataItem, identifier, resourcePath);
        log.info(gson.toJson(orderedLogMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogDebugDataItemQueriedMap(
            Class<?> dataItem,
            String identifier,
            String resourcePath) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", String.format("Queried data type: %s with identifier: %s", dataItem.getSimpleName(), identifier));
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("source(s)", resourcePath);
        return baseOrderedLogMap;
    }

    public static void logDebugDataItemQueried(
            Class<?> dataItem,
            String identifier,
            String resourcePath,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedLogMap = buildBaseLogDebugDataItemQueriedMap(dataItem, identifier, resourcePath);
        orderedLogMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedLogMap, LinkedHashMap.class));
    }

    public static void logErrorFailedDataItemQuery(
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions) {
        Map<String, Object> orderedLogMap = new LinkedHashMap<>();
        orderedLogMap.put("event", String.format("Failure to query database item(s) of type %s", dataItem.getSimpleName()));
        orderedLogMap.put("error", error.getMessage());
        orderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        orderedLogMap.put("source(s)", resourcePath);
        orderedLogMap.put("action(s)", actions);
        log.info(gson.toJson(orderedLogMap, LinkedHashMap.class));
    }

    public static void logInfoDataItemSaved(
            Class<?> dataItem,
            String identifier,
            String destinationPath) {
        Map<String, Object> orderedLogMap = buildBaseLogInfoDataItemSavedMap(dataItem, identifier, destinationPath);
        log.info(gson.toJson(orderedLogMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogInfoDataItemSavedMap(
            Class<?> dataItem,
            String identifier,
            String destinationPath) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", String.format("Saved data type: %s to repository with identifier: %s", dataItem.getSimpleName(), identifier));
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("destination(s)", destinationPath);
        return baseOrderedLogMap;
    }

    public static void logInfoDataItemSaved(
            Class<?> dataItem,
            String identifier,
            String destinationPath,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedLogMap = buildBaseLogDebugDataItemQueriedMap(dataItem, identifier, destinationPath);
        orderedLogMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedLogMap, LinkedHashMap.class));
    }

    public static String buildDbUri(String host, String port) {
        return host.concat(":").concat(port);
    }
}
