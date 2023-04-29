package chiba.dev.repos;

import chiba.dev.models.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EventModelRepo extends MongoRepository<EventModel,String> {

    List<EventModel> findByAggregateId(String aggregateId);
    Optional<EventModel> findFirstByAggregateIdOrderByOperationTimeDesc(String aggregateId);
}
