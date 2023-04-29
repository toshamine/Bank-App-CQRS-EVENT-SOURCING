package chiba.dev.CQRSCORE.dtos;

import chiba.dev.CQRSCORE.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class EventStoreDTO {
    private List<BaseEvent> events;
    private Long version;
}
