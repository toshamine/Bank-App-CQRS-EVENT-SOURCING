package chiba.dev.CQRSCORE.infrastructure.handelrs;

import chiba.dev.CQRSCORE.dtos.QueryResponse;
import chiba.dev.CQRSCORE.queries.BaseQuery;

@FunctionalInterface
public interface QueryHandlerMethod<T> {
    QueryResponse handle(T query);
}
