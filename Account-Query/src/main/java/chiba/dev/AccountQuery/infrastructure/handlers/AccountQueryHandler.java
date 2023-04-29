package chiba.dev.AccountQuery.infrastructure.handlers;


import chiba.dev.CQRSCORE.dtos.QueryResponse;
import chiba.dev.AccountQuery.enums.EquityType;
import chiba.dev.AccountQuery.models.BankAccount;
import chiba.dev.AccountQuery.queries.FindAllQuery;
import chiba.dev.AccountQuery.queries.FindByAccountHolderQuery;
import chiba.dev.AccountQuery.queries.FindByBalance;
import chiba.dev.AccountQuery.queries.FindByIdQuery;
import chiba.dev.AccountQuery.repo.BankAccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountQueryHandler implements QueryHandler {

    private final BankAccountRepo repo;

    @Override
    public QueryResponse<List<BankAccount>> handle(FindAllQuery query) {
        List<BankAccount> bankAccounts = repo.findAll();
        return new QueryResponse<>(bankAccounts);
    }

    @Override
    public QueryResponse<BankAccount> handle(FindByIdQuery query) {
        BankAccount bankAccount = repo.findById(query.getId()).get();
        return new QueryResponse<>(bankAccount);
    }

    @Override
    public QueryResponse<BankAccount> handle(FindByAccountHolderQuery query) {
        BankAccount bankAccount = repo.findByAccountHolder(query.getAccountHolder()).get();
        return new QueryResponse<>(bankAccount);
    }

    @Override
    public QueryResponse<List<BankAccount>> handle(FindByBalance query) {
        List<BankAccount> bankAccounts = query.getEquityType().equals(EquityType.GREATER_THAN) ?
                                            repo.findByBalanceGreaterThan(query.getAmount()) :
                                            repo.findByBalanceLessThan(query.getAmount());
        return new QueryResponse<>(bankAccounts);
    }
}
