package chiba.dev.infrastructure.eventSourcingHandlers;

import chiba.dev.CQRSCORE.dtos.EventStoreDTO;
import chiba.dev.CQRSCORE.infrastructure.eventStores.eventSourcingHandlers.EventSourcingHandler;
import chiba.dev.CQRSCORE.infrastructure.eventStores.EventStore;
import chiba.dev.CQRSCORE.infrastructure.producers.EventProducer;
import chiba.dev.infrastructure.AccountAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    private final EventStore eventStore;

    private final EventProducer eventProducer;

    @Override
    public void save(AccountAggregate aggregate) {
        eventStore.saveEvents(aggregate.getId(),aggregate.getUnCommittedChanges(),aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getAggregate(String aggregateId) {
        AccountAggregate accountAggregate = new AccountAggregate();
        EventStoreDTO eventStoreDTO = eventStore.getEvents(aggregateId);
        accountAggregate.replayEvents(eventStoreDTO.getEvents());
        accountAggregate.setVersion(eventStoreDTO.getVersion());
        return accountAggregate;
    }

    @Override
    public void republishEvents() {
        List<String> aggregateIds = eventStore.getAllIds();
        aggregateIds.stream()
                .map(this::getAggregate)
                .filter(AccountAggregate::getActive)
                .map(aggregate -> eventStore.getEvents(aggregate.getId()))
                .flatMap(eventStoreDTO -> eventStoreDTO.getEvents().stream())
                .forEach(event -> eventProducer.produce(event.getClass().getSimpleName(),event));
    }
}
