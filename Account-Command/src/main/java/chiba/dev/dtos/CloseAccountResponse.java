package chiba.dev.dtos;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import lombok.Data;

@Data
public class CloseAccountResponse extends BaseResponse {

    private String id;

    public CloseAccountResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
