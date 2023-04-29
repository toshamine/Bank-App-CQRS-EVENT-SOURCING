package chiba.dev.AccountQuery.queries;

import chiba.dev.CQRSCORE.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByIdQuery extends BaseQuery {
    private String id;
}
