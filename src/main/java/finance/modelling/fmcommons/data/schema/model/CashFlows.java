package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("cash_flows")
@SuperBuilder
@Data
public class CashFlows {
    private String symbol;
    private List<CashFlow> cashFlows;
}
