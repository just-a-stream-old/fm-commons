package finance.modelling.fmcommons.data.schema.eod.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EodDateOHLCAVDTO {
    @JsonProperty("date") private String timestamp;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    @JsonProperty("adjusted_close") private Double adjustedClose;
    private Long volume;
}
