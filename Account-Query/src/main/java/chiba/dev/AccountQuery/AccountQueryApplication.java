package chiba.dev.AccountQuery;






import chiba.dev.AccountQuery.infrastructure.handlers.QueryHandler;
import chiba.dev.AccountQuery.queries.FindAllQuery;
import chiba.dev.AccountQuery.queries.FindByAccountHolderQuery;
import chiba.dev.AccountQuery.queries.FindByBalance;
import chiba.dev.AccountQuery.queries.FindByIdQuery;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.QueryDispatcher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class AccountQueryApplication {

	private final QueryDispatcher queryDispatcher;
	private final QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(AccountQueryApplication.class, args);
	}


	@PostConstruct
	public void registerHandler(){
		queryDispatcher.registerHandler(FindAllQuery.class,queryHandler::handle);
		queryDispatcher.registerHandler(FindByIdQuery.class,queryHandler::handle);
		queryDispatcher.registerHandler(FindByBalance.class,queryHandler::handle);
		queryDispatcher.registerHandler(FindByAccountHolderQuery.class,queryHandler::handle);
	}



}
