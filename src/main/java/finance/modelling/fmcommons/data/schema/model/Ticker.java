package finance.modelling.fmcommons.data.schema.model;

import finance.modelling.fmcommons.data.schema.model.enums.FinanceApi;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("tickers")
@Data
public class Ticker {
    private String symbol;
    private String name;
    private String exchangeCode;
    private String country;
    private String currency;
    private String type;
    private List<FinanceApi> financeApis;
}
