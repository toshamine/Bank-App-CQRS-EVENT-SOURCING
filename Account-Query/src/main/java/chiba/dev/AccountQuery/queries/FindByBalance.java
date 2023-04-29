package chiba.dev.AccountQuery.queries;

import chiba.dev.AccountQuery.enums.EquityType;
import chiba.dev.CQRSCORE.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindByBalance extends BaseQuery {
    private Double amount;
    private EquityType equityType;
}
