package chiba.dev.models;

import chiba.dev.CQRSCORE.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EventModel {

    @Id
    private String id;
    private String aggregateId;
    private String aggregateType;
    private String eventType;
    private BaseEvent eventData;
    private Long version;
    private LocalDateTime operationTime;
}
