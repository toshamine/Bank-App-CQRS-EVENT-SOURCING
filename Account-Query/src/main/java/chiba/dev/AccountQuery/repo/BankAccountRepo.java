package chiba.dev.AccountQuery.repo;

import chiba.dev.AccountQuery.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepo extends JpaRepository<BankAccount,String> {
    Optional<BankAccount> findByAccountHolder(String accountHolder);
    List<BankAccount> findByBalanceGreaterThan(Double balance);
    List<BankAccount> findByBalanceLessThan(Double balance);
}
