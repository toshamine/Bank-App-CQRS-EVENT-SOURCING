package chiba.dev.AccountQuery.infrastructure.consumers;

import chiba.dev.AccountQuery.infrastructure.handlers.AccountEventHandler;
import chiba.dev.events.CloseAccountEvent;
import chiba.dev.events.DepositFundsEvent;
import chiba.dev.events.OpenAccountEvent;
import chiba.dev.events.WithdrawFundsEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountEventConsumer implements EventConsumer{

    private final AccountEventHandler eventHandler;

    @KafkaListener(topics = "OpenAccountEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(OpenAccountEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "DepositFundsEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(DepositFundsEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "WithdrawFundsEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(WithdrawFundsEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "CloseAccountEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(CloseAccountEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
