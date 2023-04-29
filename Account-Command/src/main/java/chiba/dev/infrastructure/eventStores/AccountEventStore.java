package chiba.dev.infrastructure.eventStores;

import chiba.dev.CQRSCORE.dtos.EventStoreDTO;
import chiba.dev.CQRSCORE.events.BaseEvent;
import chiba.dev.CQRSCORE.expcetions.AggregateException;
import chiba.dev.CQRSCORE.expcetions.ConcurrencyException;
import chiba.dev.CQRSCORE.infrastructure.eventStores.EventStore;
import chiba.dev.CQRSCORE.infrastructure.producers.EventProducer;
import chiba.dev.infrastructure.AccountAggregate;
import chiba.dev.models.EventModel;
import chiba.dev.repos.EventModelRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountEventStore implements EventStore {

    private final EventModelRepo repo;
    private final EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, List<BaseEvent> events, Long expectedVersion) {
        EventModel lastEvent = repo.findFirstByAggregateIdOrderByOperationTimeDesc(aggregateId).orElse(new EventModel());
        System.out.println(lastEvent.getVersion());
        System.out.println(expectedVersion);
        if(expectedVersion != -1 && lastEvent.getVersion() != expectedVersion)
            throw ConcurrencyException.INCORRECT_VERSION;
        long version = expectedVersion;
        for(var event:events){
            version++;
            EventModel eventModel = EventModel.builder()
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .aggregateId(aggregateId)
                    .operationTime(event.getOperationTime())
                    .version(version)
                    .build();
            repo.save(eventModel);
            eventProducer.produce(event.getClass().getSimpleName(),event);
        }
    }

    @Override
    public EventStoreDTO getEvents(String aggregateId) {
        List<EventModel> storedEvents = repo.findByAggregateId(aggregateId);
        if(storedEvents == null || storedEvents.size() == 0)
            throw AggregateException.NOT_FOUND;
        List<BaseEvent> events = storedEvents.stream()
                .map(EventModel::getEventData)
                .collect(Collectors.toList());
        Long version = storedEvents.stream()
                .mapToLong(EventModel::getVersion)
                .max()
                .getAsLong();

        return EventStoreDTO.builder()
                .events(events)
                .version(version)
                .build();
    }

    @Override
    public List<String> getAllIds() {
        return repo.findAll()
                .stream()
                .map(EventModel::getAggregateId)
                .collect(Collectors.toList());
    }
}
