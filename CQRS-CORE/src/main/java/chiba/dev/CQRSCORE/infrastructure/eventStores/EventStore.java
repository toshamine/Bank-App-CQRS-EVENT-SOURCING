package chiba.dev.CQRSCORE.infrastructure.eventStores;

import chiba.dev.CQRSCORE.dtos.EventStoreDTO;
import chiba.dev.CQRSCORE.events.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, List<BaseEvent> events, Long expectedVersion);
    EventStoreDTO getEvents(String aggregateId);

    List<String> getAllIds();
}
