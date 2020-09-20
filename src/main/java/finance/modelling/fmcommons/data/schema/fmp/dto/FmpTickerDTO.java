package finance.modelling.fmcommons.data.schema.fmp.dto;

import lombok.Data;

@Data
public class FmpTickerDTO {
    private String symbol;
    private String name;
    private String price;
    private String exchange;
}
