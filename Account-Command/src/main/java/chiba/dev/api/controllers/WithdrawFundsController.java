package chiba.dev.api.controllers;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.CommandDispatcher;
import chiba.dev.commands.WithdrawFundsCommand;
import chiba.dev.dtos.WithdrawFundsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/withdraw-funds")
@RequiredArgsConstructor
@Slf4j
public class WithdrawFundsController {

    private final CommandDispatcher commandDispatcher;

    @PutMapping
    public ResponseEntity<BaseResponse> withdrawFunds(@RequestBody WithdrawFundsCommand command){
        try{
            commandDispatcher.send(command);
            return new ResponseEntity<>(new WithdrawFundsResponse("Amount was withdrawen", command.getAmount()), HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
            return new ResponseEntity<>(new BaseResponse(exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
