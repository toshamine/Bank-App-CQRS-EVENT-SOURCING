package chiba.dev.CQRSCORE.infrastructure.producers;

import chiba.dev.CQRSCORE.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
