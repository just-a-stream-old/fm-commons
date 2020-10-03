package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;

@Data
public class DateOLHCAV {
    private String symbol;
    private String timestamp;
    private Double open;
    private Double low;
    private Double high;
    private Double close;
    private Double adjustedClose;
    private Long volume;
}
