package chiba.dev.infrastructure.producers;

import chiba.dev.CQRSCORE.events.BaseEvent;
import chiba.dev.CQRSCORE.infrastructure.producers.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountEventProducer implements EventProducer {

    private final KafkaTemplate<String,BaseEvent> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event) {
        kafkaTemplate.send(topic,event);
    }
}
