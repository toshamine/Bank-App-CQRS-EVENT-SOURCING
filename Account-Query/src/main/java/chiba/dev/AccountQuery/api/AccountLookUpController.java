package chiba.dev.AccountQuery.api;

import chiba.dev.AccountQuery.queries.FindAllQuery;
import chiba.dev.AccountQuery.queries.FindByAccountHolderQuery;
import chiba.dev.AccountQuery.queries.FindByBalance;
import chiba.dev.AccountQuery.queries.FindByIdQuery;
import chiba.dev.CQRSCORE.dtos.QueryResponse;
import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.QueryDispatcher;
import chiba.dev.CQRSCORE.queries.BaseQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.text.MessageFormat;

@RestController
@RequestMapping("api/v1/look-up")
@RequiredArgsConstructor
@Slf4j
public class AccountLookUpController {

    private final QueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<BaseResponse> findAllAccounts(@RequestBody BaseQuery query){
        try{
            QueryResponse queryResponse = queryDispatcher.send(query);
            return new ResponseEntity<>(
                    new QueryResponse<>(queryResponse)
                    , HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
            return new ResponseEntity<>(new BaseResponse(exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
