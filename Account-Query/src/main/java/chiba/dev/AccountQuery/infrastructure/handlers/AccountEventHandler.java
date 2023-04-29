package chiba.dev.AccountQuery.infrastructure.handlers;

import chiba.dev.AccountQuery.models.BankAccount;
import chiba.dev.AccountQuery.repo.BankAccountRepo;
import chiba.dev.events.CloseAccountEvent;
import chiba.dev.events.DepositFundsEvent;
import chiba.dev.events.OpenAccountEvent;
import chiba.dev.events.WithdrawFundsEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountEventHandler implements EventHandler{

    private final BankAccountRepo repo;

    @Override
    public void on(OpenAccountEvent event) {
        BankAccount bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .accountType(event.getAccountType())
                .balance(event.getBalance())
                .build();
        repo.save(bankAccount);
    }

    @Override
    public void on(DepositFundsEvent event) {
        BankAccount bankAccount = repo.findById(event.getId()).get();
        bankAccount.setBalance(bankAccount.getBalance() + event.getAmount());
        repo.save(bankAccount);
    }

    @Override
    public void on(WithdrawFundsEvent event) {
        BankAccount bankAccount = repo.findById(event.getId()).get();
        bankAccount.setBalance(bankAccount.getBalance() - event.getAmount());
        repo.save(bankAccount);
    }

    @Override
    public void on(CloseAccountEvent event) {
        repo.deleteById(event.getId());
    }
}
