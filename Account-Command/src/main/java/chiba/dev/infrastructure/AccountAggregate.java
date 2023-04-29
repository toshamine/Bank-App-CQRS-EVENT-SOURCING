package chiba.dev.infrastructure;

import chiba.dev.CQRSCORE.infrastructure.AggregateRoot;
import chiba.dev.commands.CloseAccountCommand;
import chiba.dev.commands.DepositFundsCommand;
import chiba.dev.commands.OpenAccountCommand;
import chiba.dev.commands.WithdrawFundsCommand;
import chiba.dev.events.CloseAccountEvent;
import chiba.dev.events.DepositFundsEvent;
import chiba.dev.events.OpenAccountEvent;
import chiba.dev.events.WithdrawFundsEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class AccountAggregate extends AggregateRoot {
    private double balance;
    private Boolean active;

    public AccountAggregate(OpenAccountCommand command){
        raiseEvent(OpenAccountEvent.builder()
                .id(command.getId())
                .accountType(command.getAccountType())
                .accountHolder(command.getAccountHolder())
                .balance(command.getBalance())
                .operationTime(LocalDateTime.now())
                .build());
    }

    public void apply(OpenAccountEvent event){
        this.id = event.getId();
        this.balance = event.getBalance();
        this.active = true;
    }

    public void depositFunds(DepositFundsCommand command){
        if(command.getAmount() <= 0){
            throw new IllegalArgumentException("The amount must be greater than 0");
        }

        if(!this.active){
            throw new IllegalStateException("The account is closed");
        }

        raiseEvent(DepositFundsEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .operationTime(LocalDateTime.now())
                .build());
    }

    public void apply(DepositFundsEvent event){
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(WithdrawFundsCommand command){
        if(command.getAmount() > this.balance){
            throw new IllegalArgumentException("The amount must be less than the balance");
        }

        if(!this.active){
            throw new IllegalStateException("The account is closed");
        }

        raiseEvent(WithdrawFundsEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .operationTime(LocalDateTime.now())
                .build());
    }

    public void apply(WithdrawFundsEvent event){
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount(CloseAccountCommand command){
        if(!this.active){
            throw new IllegalStateException("The account is closed");
        }
        raiseEvent(CloseAccountEvent.builder()
                .id(command.getId())
                .operationTime(LocalDateTime.now())
                .build());
    }

    public void apply(CloseAccountEvent event){
        this.id = event.getId();
        this.active = false;
    }
}
