package chiba.dev.AccountQuery.infrastructure.handlers;

import chiba.dev.CQRSCORE.dtos.QueryResponse;
import chiba.dev.AccountQuery.models.BankAccount;
import chiba.dev.AccountQuery.queries.FindAllQuery;
import chiba.dev.AccountQuery.queries.FindByAccountHolderQuery;
import chiba.dev.AccountQuery.queries.FindByBalance;
import chiba.dev.AccountQuery.queries.FindByIdQuery;

import java.util.List;

public interface QueryHandler {
    QueryResponse<List<BankAccount>> handle(FindAllQuery query);
    QueryResponse<BankAccount> handle(FindByIdQuery query);
    QueryResponse<BankAccount> handle(FindByAccountHolderQuery query);
    QueryResponse<List<BankAccount>> handle(FindByBalance query);
}
