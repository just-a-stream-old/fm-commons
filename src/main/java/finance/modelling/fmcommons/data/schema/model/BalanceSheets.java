package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("balance_sheets")
@Data
public class BalanceSheets {

    private String symbol;
    private List<BalanceSheet> balanceSheets;
}
