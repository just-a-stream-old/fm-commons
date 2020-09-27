package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;

@Data
public class IncomeStatement {
    private String symbol;
    private String date;
    private String fillingDate;
    private String acceptedDate;
    private String period;
    private Integer revenue;
    private Integer costOfRevenue;
    private Integer grossProfit;
    private Double grossProfitRatio;
    private Integer researchAndDevelopmentExpenses;
    private Integer generalAndAdministrativeExpenses;
    private Double sellingAndMarketingExpenses;
    private Integer otherExpenses;
    private Integer operatingExpenses;
    private Integer costAndExpenses;
    private Integer interestExpense;
    private Integer depreciationAndAmortization;
    private Integer earningsBeforeInterestTaxesDepreciationAmortisation;
    private Double ratioEarningsBeforeInterestTaxesDepreciationAmortisation;
    private Integer operatingIncome;
    private Double operatingIncomeRatio;
    private Integer totalOtherIncomeExpensesNet;
    private Integer incomeBeforeTax;
    private Double incomeBeforeTaxRatio;
    private Integer incomeTaxExpense;
    private Integer netIncome;
    private Double netIncomeRatio;
    private Double eps;
    private Double EpsDiluted;
    private Integer weightedAverageShsOut;
    private Integer weightedAverageShsOutDil;
    private String link;
    private String finalLink;
}
