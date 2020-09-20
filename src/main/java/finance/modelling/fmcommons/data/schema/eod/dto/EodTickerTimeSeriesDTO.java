package finance.modelling.fmcommons.data.schema.eod.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EodTickerTimeSeriesDTO {
    private String symbol;
    private List<EodDateOHLCAVDTO> timeSeries;
}
