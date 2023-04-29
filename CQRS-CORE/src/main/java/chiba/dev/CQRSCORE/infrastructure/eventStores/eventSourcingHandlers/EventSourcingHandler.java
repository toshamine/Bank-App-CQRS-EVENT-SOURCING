package chiba.dev.CQRSCORE.infrastructure.eventStores.eventSourcingHandlers;

public interface EventSourcingHandler<T> {
    void save(T aggregate);
    T getAggregate(String aggregateId);

    void republishEvents();
}
