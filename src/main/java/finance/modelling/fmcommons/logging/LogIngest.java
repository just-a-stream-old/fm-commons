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
public class LogIngest {

    private static final Gson gson = new Gson();
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(("yyyy-MM-dd'T'HH:mm:ss'Z'"));

    public static void logInfoDataItemIngested(
            String ticker,
            Class<?> dataItem,
            String resourceUri,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemIngestedMap(ticker, dataItem, resourceUri);
        orderedMap.put("additionalInfo", additionalInfo);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String, Object> buildBaseLogInfoDataItemIngestedMap(
            String ticker,
            Class<?> dataItem,
            String resourceUri) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Ingested data item: %s for ticker: %s", dataItem.getSimpleName(), ticker)));
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("source(s)", resourceUri);
        return baseOrderedLogMap;
    }

    public static void logInfoDataItemIngested(
            String ticker,
            Class<?> dataItem,
            String resourceUri) {
        Map<String, Object> orderedMap = buildBaseLogInfoDataItemIngestedMap(ticker, dataItem, resourceUri);
        log.info(gson.toJson(orderedMap, LinkedHashMap.class));
    }


    public static void logErrorFailedDataItemIngestion(
            String ticker,
            Class<?> dataItem,
            Throwable error,
            String resourceUri,
            List<String> actions,
            Map<String, Object> additionalInfo) {
        Map<String, Object> orderedMap = buildBaseLogErrorFailedDataItemIngestion(
                ticker, dataItem, error, resourceUri, actions);
        orderedMap.put("additionalInfo", additionalInfo);
        log.error(gson.toJson(orderedMap, LinkedHashMap.class));
    }

    private static Map<String,Object> buildBaseLogErrorFailedDataItemIngestion(
            String ticker,
            Class<?> dataItem,
            Throwable error,
            String resourceUri,
            List<String> actions) {
        Map<String, Object> baseOrderedLogMap = new LinkedHashMap<>();
        baseOrderedLogMap.put("event", (String.format("Failure to ingest data item: %s for ticker: %s", dataItem.getSimpleName(), ticker)));
        baseOrderedLogMap.put("error", error.getMessage());
        baseOrderedLogMap.put("timestamp", dateFormatter.format(Date.from(Instant.now())));
        baseOrderedLogMap.put("source(s)", resourceUri);
        baseOrderedLogMap.put("action(s)", actions);
        return baseOrderedLogMap;
    }

    public static void logErrorFailedDataItemIngestion(
            String ticker,
            Class<?> dataItem,
            Throwable error,
            String resourceUri,
            List<String> actions) {
        Map<String, Object> orderedMap = buildBaseLogErrorFailedDataItemIngestion(
                ticker, dataItem, error, resourceUri, actions);
        log.error(gson.toJson(orderedMap, LinkedHashMap.class));
    }

}
