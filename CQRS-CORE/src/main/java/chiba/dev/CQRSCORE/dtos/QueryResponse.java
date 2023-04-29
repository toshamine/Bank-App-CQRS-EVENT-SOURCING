package chiba.dev.CQRSCORE.dtos;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryResponse <T> extends BaseResponse {
    private T data;


}
