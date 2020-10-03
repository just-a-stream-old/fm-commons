package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("time_series_d1")
@SuperBuilder
@Data
public class TimeSeriesD1 {
    private String timestamp;
    private Double open;
    private Double low;
    private Double high;
    private Double close;
    private Double adjustedClose;
    private Long volume;
}
