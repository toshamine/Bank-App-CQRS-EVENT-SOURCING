package chiba.dev.CQRSCORE.infrastructure.dispatchers;

import chiba.dev.CQRSCORE.commands.BaseCommand;
import chiba.dev.CQRSCORE.infrastructure.handelrs.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
