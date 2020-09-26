package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;

@Data
public class BalanceSheet {
    private String symbol;
    private String date;
    private String fillingDate;
    private String acceptedDate;
    private String period;
    private Long cashAndCashEquivalents;
    private Long shortTermInvestments;
    private Long cashAndShortTermInvestments;
    private Long netReceivables;
    private Long inventory;
    private Long otherCurrentAssets;
    private Long totalCurrentAssets;
    private Long propertyPlantEquipmentNet;
    private Double goodwill;
    private Double intangibleAssets;
    private Double goodwillAndIntangibleAssets;
    private Long longTermInvestments;
    private Double taxAssets;
    private Long otherNonCurrentAssets;
    private Long totalNonCurrentAssets;
    private Long otherAssets;
    private Long totalAssets;
    private Long accountPayables;
    private Long shortTermDebt;
    private Double taxPayables;
    private Long deferredRevenue;
    private Long otherCurrentLiabilities;
    private Long totalCurrentLiabilities;
    private Long longTermDebt;
    private Double deferredRevenueNonCurrent;
    private Double deferredTaxLiabilitiesNonCurrent;
    private Long otherNonCurrentLiabilities;
    private Long totalNonCurrentLiabilities;
    private Long otherLiabilities;
    private Long totalLiabilities;
    private Long commonStock;
    private Long retainedEarnings;
    private Long accumulatedOtherComprehensiveIncomeLoss;
    private Long otherTotalStockholdersEquity;
    private Long totalStockholdersEquity;
    private Long totalLiabilitiesAndStockholdersEquity;
    private Long totalInvestments;
    private Long totalDebt;
    private Long netDebt;
    private String link;
    private String finalLink;
}
