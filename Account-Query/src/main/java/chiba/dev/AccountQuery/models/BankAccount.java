package chiba.dev.AccountQuery.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccount {

    @Id
    private String id;
    private String accountHolder;
    private String accountType;
    private Double balance;
    @CreationTimestamp
    private LocalDateTime creationTime;
}
