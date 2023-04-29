package chiba.dev.AccountQuery.infrastructure.consumers;

import chiba.dev.events.CloseAccountEvent;
import chiba.dev.events.DepositFundsEvent;
import chiba.dev.events.OpenAccountEvent;
import chiba.dev.events.WithdrawFundsEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload OpenAccountEvent event, Acknowledgment ack);
    void consume(@Payload DepositFundsEvent event, Acknowledgment ack);
    void consume(@Payload WithdrawFundsEvent event, Acknowledgment ack);
    void consume(@Payload CloseAccountEvent event, Acknowledgment ack);
}
