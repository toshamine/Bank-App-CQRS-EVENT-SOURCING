package chiba.dev.infrastructure.dispatchers;

import chiba.dev.CQRSCORE.commands.BaseCommand;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.CommandDispatcher;
import chiba.dev.CQRSCORE.infrastructure.handelrs.CommandHandlerMethod;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>,CommandHandlerMethod> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        routes.computeIfAbsent(type,h -> handler);
    }

    @Override
    public void send(BaseCommand command) {
        CommandHandlerMethod handler = routes.get(command.getClass());
        if(handler == null)
            throw new NoSuchElementException(MessageFormat.format("Handler for command type {0} not found",command.getClass().getSimpleName()));
        handler.handle(command);
    }
}
