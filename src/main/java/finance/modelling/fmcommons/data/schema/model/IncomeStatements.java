package finance.modelling.fmcommons.data.schema.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("income_statements")
@SuperBuilder
@Data
public class IncomeStatements {
    private String symbol;
    private List<IncomeStatement> incomeStatements;
}
