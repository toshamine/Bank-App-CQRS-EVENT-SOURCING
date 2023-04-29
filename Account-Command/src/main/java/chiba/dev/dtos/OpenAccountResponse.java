package chiba.dev.dtos;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String message,String id){
        super(message);
        this.id = id;
    }
}
