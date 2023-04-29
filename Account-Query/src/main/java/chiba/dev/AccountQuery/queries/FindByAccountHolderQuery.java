package chiba.dev.AccountQuery.queries;

import chiba.dev.CQRSCORE.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindByAccountHolderQuery extends BaseQuery {
    private String accountHolder;
}
