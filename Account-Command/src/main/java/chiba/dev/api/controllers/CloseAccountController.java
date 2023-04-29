package chiba.dev.api.controllers;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.CommandDispatcher;
import chiba.dev.commands.CloseAccountCommand;
import chiba.dev.dtos.CloseAccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/close-account")
@RequiredArgsConstructor
@Slf4j
public class CloseAccountController {

    private final CommandDispatcher commandDispatcher;

    @DeleteMapping
    public ResponseEntity<BaseResponse> closeAccount(@RequestBody CloseAccountCommand command){
        try{
            commandDispatcher.send(command);
            return new ResponseEntity<>(new CloseAccountResponse("Account was closed", command.getId()), HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
            return new ResponseEntity<>(new BaseResponse(exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
