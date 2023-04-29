package chiba.dev.api.controllers;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.CommandDispatcher;
import chiba.dev.commands.DepositFundsCommand;
import chiba.dev.dtos.DepositFundsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/v1/deposit-funds")
@RequiredArgsConstructor
@Slf4j
public class DepositFundsController {

    private final CommandDispatcher commandDispatcher;

    @PutMapping
    public ResponseEntity<BaseResponse> depositFunds(@RequestBody DepositFundsCommand command){
        try{
            commandDispatcher.send(command);
            return new ResponseEntity<>(new DepositFundsResponse("The amount was deposited", command.getAmount()), HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
            return new ResponseEntity<>(new BaseResponse(exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


}
