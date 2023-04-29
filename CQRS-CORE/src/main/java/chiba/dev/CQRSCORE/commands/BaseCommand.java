package chiba.dev.CQRSCORE.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseCommand {
    private String id;
}
