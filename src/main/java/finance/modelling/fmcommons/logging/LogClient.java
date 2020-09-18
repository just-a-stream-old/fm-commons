package finance.modelling.fmcommons.logging;

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
            String ticker,
            Class<?> dataItem,
            String resourcePath,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemReceivedMap(ticker, dataItem, resourcePath);
        orderedMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogInfoDataItemReceivedMap(
            String ticker,
            Class<?> dataItem,
            String resourcePath) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Received data item: %s for ticker: %s", dataItem.getSimpleName(), ticker)));
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("source(s)", resourcePath);
        return baseOrderedLogMap;
    }

    public static void logInfoDataItemReceived(
            String ticker,
            Class<?> dataItem,
            String resourcePath) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemReceivedMap(ticker, dataItem, resourcePath);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }


    public static void logErrorFailedToReceiveDataItem(
            String ticker,
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogErrorFailedToReceiveDataItem(
                ticker, dataItem, error, resourcePath, actions);
        orderedMap.put("additionalInfo", additionalInfo);
        log.error(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String,Object> buildBaseLogErrorFailedToReceiveDataItem(
            String ticker,
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Failure to receive data item: %s for ticker: %s", dataItem.getSimpleName(), ticker)));
        baseOrderedLogMap.put("error", error.getMessage());
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("source(s)", resourcePath);
        baseOrderedLogMap.put("action(s)", actions);
        return baseOrderedLogMap;
    }

    public static void logErrorFailedToReceiveDataItem(
            String ticker,
            Class<?> dataItem,
            Throwable error,
            String resourcePath,
            List<String> actions) {
        Map<String, Object> orderedMap = buildBaseLogErrorFailedToReceiveDataItem(
                ticker, dataItem, error, resourcePath, actions);
        log.error(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    public static String buildResourcePath(String baseUrl, String resourceUrl) {
        return baseUrl.concat("/").concat(resourceUrl);
    }


}
