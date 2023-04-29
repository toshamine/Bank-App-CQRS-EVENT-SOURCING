package chiba.dev.AccountQuery.infrastructure.dispatchers;

import chiba.dev.CQRSCORE.dtos.QueryResponse;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.QueryDispatcher;
import chiba.dev.CQRSCORE.infrastructure.handelrs.QueryHandlerMethod;
import chiba.dev.CQRSCORE.queries.BaseQuery;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>,QueryHandlerMethod> routes = new HashMap<>();


    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        routes.computeIfAbsent(type,h -> handler);
    }

    @Override
    public QueryResponse send(BaseQuery query) {
        QueryHandlerMethod handler = routes.get(query.getClass());
        if(handler == null)
            throw new NoSuchElementException(MessageFormat.format("No handler was found for query type {0}",query.getClass().getSimpleName()));
        return handler.handle(query);
    }
}
