package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;

import java.util.List;

@Data
public class Exchange {
    private String exchangeCode;
    private String name;
    private String marketIdCode;
    private String country;
    private String currency;
    private List<FinanceApi> financeApis;
}
