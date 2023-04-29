package chiba.dev.dtos;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import lombok.Data;

@Data
public class WithdrawFundsResponse extends BaseResponse {

    private Double amount;

    public WithdrawFundsResponse(String message, Double amount) {
        super(message);
        this.amount = amount;
    }
}

