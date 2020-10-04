package finance.modelling.fmcommons.data.schema.eod.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EodTickerTimeSeriesDTO {
    private String symbol;
    private List<EodDateOHLCAVDTO> timeSeries;
}
