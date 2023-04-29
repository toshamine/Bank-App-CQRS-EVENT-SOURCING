package chiba.dev.infrastructure.handlers;

import chiba.dev.CQRSCORE.infrastructure.eventStores.eventSourcingHandlers.EventSourcingHandler;
import chiba.dev.commands.*;
import chiba.dev.infrastructure.AccountAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommandHandler implements CommandHandler {

    private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    @Override
    public void handle(OpenAccountCommand command) {
        AccountAggregate accountAggregate = new AccountAggregate(command);
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(WithdrawFundsCommand command) {
        AccountAggregate accountAggregate = eventSourcingHandler.getAggregate(command.getId());
        accountAggregate.withdrawFunds(command);
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(DepositFundsCommand command) {
        AccountAggregate accountAggregate = eventSourcingHandler.getAggregate(command.getId());
        accountAggregate.depositFunds(command);
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(CloseAccountCommand command) {
        AccountAggregate accountAggregate = eventSourcingHandler.getAggregate(command.getId());
        accountAggregate.closeAccount(command);
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(RestoreDbCommand command) {
        eventSourcingHandler.republishEvents();
    }
}
