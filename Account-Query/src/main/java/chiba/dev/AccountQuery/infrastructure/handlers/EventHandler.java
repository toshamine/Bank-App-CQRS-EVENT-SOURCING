package chiba.dev.AccountQuery.infrastructure.handlers;

import chiba.dev.events.CloseAccountEvent;
import chiba.dev.events.DepositFundsEvent;
import chiba.dev.events.OpenAccountEvent;
import chiba.dev.events.WithdrawFundsEvent;

public interface EventHandler {
    void on(OpenAccountEvent event);
    void on(DepositFundsEvent event);
    void on(WithdrawFundsEvent event);
    void on(CloseAccountEvent event);
}
