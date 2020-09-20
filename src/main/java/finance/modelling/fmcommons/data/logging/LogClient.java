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
public class LogClient {

    private static final Gson gson = new Gson();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss'Z'"));

    public static void logInfoDataItemReceived(
            String identifier,
            Class<?> dataItem,
            String resourcePath,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemReceivedMap(identifier, dataItem, resourcePath);
        orderedMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogInfoDataItemReceivedMap(
            String identifier,
            Class<?> dataItem,
            String resourcePath) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Received data item: %s for identifier: %s", dataItem.getSimpleName(), identifier)));
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("source(s)", resourcePath);
        return baseOrderedLogMap;
    }

    public static void logInfoDataItemReceived(
            String identifier,
            Class<?> dataItem,
            String resourcePath) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemReceivedMap(identifier, dataItem, resourcePath);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }


    public static void logErrorFailedToReceiveDataItem(
            String identifier,
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogFailedToReceiveDataItem(
                identifier, dataItem, error, resourcePath, actions);
        orderedMap.put("additionalInfo", additionalInfo);
        log.error(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String,Object> buildBaseLogFailedToReceiveDataItem(
            String identifier,
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Failure to receive data item: %s for identifier: %s", dataItem.getSimpleName(), identifier)));
        baseOrderedLogMap.put("error", getSimpleNameAndMessage(error));
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("source(s)", resourcePath);
        baseOrderedLogMap.put("action(s)", actions);
        return baseOrderedLogMap;
    }

    public static void logErrorFailedToReceiveDataItem(
            String identifier,
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions) {
        Map<String, Object> orderedMap = buildBaseLogFailedToReceiveDataItem(
                identifier, dataItem, error, resourcePath, actions);
        log.error(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    public static void logDebugFailedToReceiveDataItem(
            String identifier,
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogFailedToReceiveDataItem(
                identifier, dataItem, error, resourcePath, actions);
        orderedMap.put("additionalInfo", additionalInfo);
        log.debug(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    public static void logDebugFailedToReceiveDataItem(
            String identifier,
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions) {
        Map<String, Object> orderedMap = buildBaseLogFailedToReceiveDataItem(
                identifier, dataItem, error, resourcePath, actions);
        log.debug(gson.toJson(orderedMap, LinkedHashMap.class));
    }



    public static void logInfoProcessComplete(String processName) {
        Map<String, Object> orderedMap = buildBaseLogInfoProcessComplete(processName);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogInfoProcessComplete(String processName) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("Process", processName);
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        return baseOrderedLogMap;
    }

    public static void logInfoProcessComplete(String processName, Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogInfoProcessComplete(processName);
        orderedMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    public static String buildResourcePath(String baseUrl, String resourceUrl) {
        return baseUrl.concat("/").concat(resourceUrl);
    }

    public static String getSimpleNameAndMessage(Throwable error) {
        return error.getClass().getSimpleName().concat(": ").concat(error.getMessage());
    }


}
