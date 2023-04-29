package chiba.dev;




import chiba.dev.CQRSCORE.infrastructure.dispatchers.CommandDispatcher;
import chiba.dev.CQRSCORE.infrastructure.handelrs.CommandHandlerMethod;
import chiba.dev.commands.*;
import chiba.dev.infrastructure.handlers.CommandHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@RequiredArgsConstructor
public class AccountCommandApplication {


	private final CommandDispatcher commandDispatcher;
	private final CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(AccountCommandApplication.class, args);
	}


	@PostConstruct
	public void registerHandlers(){
		commandDispatcher.registerHandler(OpenAccountCommand.class,commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundsCommand.class,commandHandler::handle);
		commandDispatcher.registerHandler(WithdrawFundsCommand.class,commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class,commandHandler::handle);
		commandDispatcher.registerHandler(RestoreDbCommand.class,commandHandler::handle);
	}






}
