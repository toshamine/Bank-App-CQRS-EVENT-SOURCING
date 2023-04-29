package chiba.dev.commands;

import chiba.dev.CQRSCORE.commands.BaseCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private String accountType;
    private Double balance;
}
