package finance.modelling.fmcommons.data.schema.fmp.dto;

import lombok.Data;

import java.util.List;

@Data
public class FmpBalanceSheetsDTO {
    private String symbol;
    private List<FmpBalanceSheetDTO> balanceSheets;
}
