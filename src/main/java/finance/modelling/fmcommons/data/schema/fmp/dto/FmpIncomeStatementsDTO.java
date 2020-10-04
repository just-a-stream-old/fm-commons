package finance.modelling.fmcommons.data.schema.fmp.dto;

import lombok.Data;

import java.util.List;

@Data
public class FmpIncomeStatementsDTO {
    private String symbol;
    private List<FmpIncomeStatementDTO> incomeStatements;
}
