package chiba.dev.CQRSCORE.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEvent {
    private String id;
    private LocalDateTime operationTime;
}
