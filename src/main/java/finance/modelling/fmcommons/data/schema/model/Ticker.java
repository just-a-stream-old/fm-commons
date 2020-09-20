package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;

@Data
public class Ticker {
    private String symbol;
    private String name;
    private String exchangeCode;
    private String country;
    private String currency;
    private String type;
}
