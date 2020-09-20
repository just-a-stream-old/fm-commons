package finance.modelling.fmcommons.data.schema.fmp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FmpIncomeStatementsDTO {
    private String symbol;
    private List<FmpIncomeStatementDTO> incomeStatements;
}
