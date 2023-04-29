package chiba.dev.CQRSCORE.infrastructure.handelrs;

import chiba.dev.CQRSCORE.commands.BaseCommand;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(T command);
}
