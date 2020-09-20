package finance.modelling.fmcommons.data.schema.fmp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FmpCashFlowsDTO {
    private String symbol;
    private List<FmpCashFlowDTO> cashFlows;
}
