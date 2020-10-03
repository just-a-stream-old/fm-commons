package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("time_series_d1")
@SuperBuilder
@Data
public class TimeSeriesD1 {
    private String symbol;
    private List<DateOLHCAV> timeSeries;
}
