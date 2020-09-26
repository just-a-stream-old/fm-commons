package finance.modelling.fmcommons.data.schema.model;

import finance.modelling.fmcommons.data.schema.model.enums.FinanceApi;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("exchanges")
@Data
public class Exchange {
    private String exchangeCode;
    private String name;
    private String marketIdCode;
    private String country;
    private String currency;
    private List<FinanceApi> financeApis;
}
