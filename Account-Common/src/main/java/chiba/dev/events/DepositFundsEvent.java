package chiba.dev.events;

import chiba.dev.CQRSCORE.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class DepositFundsEvent extends BaseEvent {
    private Double amount;
}
