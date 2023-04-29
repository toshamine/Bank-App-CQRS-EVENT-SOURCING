package chiba.dev.dtos;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DepositFundsResponse extends BaseResponse {

    private Double amount;

    public DepositFundsResponse(String message, Double amount) {
        super(message);
        this.amount = amount;
    }
}
