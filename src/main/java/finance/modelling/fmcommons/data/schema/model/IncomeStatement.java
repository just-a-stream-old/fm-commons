package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;

@Data
public class IncomeStatement {
    private String symbol;
    private String date;
    private String period;
    private Long revenue;
    private Long costOfRevenue;
    private Long grossProfit;
    private Double grossProfitRatio;
    private Long researchAndDevelopmentExpenses;
    private Long generalAndAdministrativeExpenses;
    private Double sellingAndMarketingExpenses;
    private Long otherExpenses;
    private Long operatingExpenses;
    private Long costAndExpenses;
    private Long interestExpense;
    private Long depreciationAndAmortization;
    private Long earningsBeforeInterestTaxesDepreciationAmortisation;
    private Double ratioEarningsBeforeInterestTaxesDepreciationAmortisation;
    private Long operatingIncome;
    private Double operatingIncomeRatio;
    private Long totalOtherIncomeExpensesNet;
    private Long incomeBeforeTax;
    private Double incomeBeforeTaxRatio;
    private Long incomeTaxExpense;
    private Long netIncome;
    private Double netIncomeRatio;
    private Double eps;
    private Double EpsDiluted;
    private Long weightedAverageShsOut;
    private Long weightedAverageShsOutDil;
}
