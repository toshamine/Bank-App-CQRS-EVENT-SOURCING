package chiba.dev.infrastructure.handlers;

import chiba.dev.commands.*;

public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(WithdrawFundsCommand command);
    void handle(DepositFundsCommand command);
    void handle(CloseAccountCommand command);

    void handle(RestoreDbCommand command);
}
