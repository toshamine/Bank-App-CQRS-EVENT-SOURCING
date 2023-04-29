package chiba.dev.CQRSCORE.infrastructure.dispatchers;

import chiba.dev.CQRSCORE.dtos.QueryResponse;
import chiba.dev.CQRSCORE.infrastructure.handelrs.QueryHandlerMethod;
import chiba.dev.CQRSCORE.queries.BaseQuery;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);


    QueryResponse send(BaseQuery query);
}
