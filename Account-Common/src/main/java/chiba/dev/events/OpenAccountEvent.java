package chiba.dev.events;

import chiba.dev.CQRSCORE.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
public class OpenAccountEvent extends BaseEvent {
    private String accountHolder;
    private String accountType;
    private Double balance;

}
