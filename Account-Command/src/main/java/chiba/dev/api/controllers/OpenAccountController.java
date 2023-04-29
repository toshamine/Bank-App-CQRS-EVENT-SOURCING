package chiba.dev.api.controllers;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.CommandDispatcher;
import chiba.dev.commands.OpenAccountCommand;
import chiba.dev.dtos.OpenAccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/open-bank-account")
@RequiredArgsConstructor
@Slf4j
public class OpenAccountController {

    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openBankAccount(@RequestBody OpenAccountCommand command){
        String id = UUID.randomUUID().toString();
        command.setId(id);
        try{
            commandDispatcher.send(command);
            return new ResponseEntity<>(new OpenAccountResponse("Account was created",id), HttpStatus.CREATED);
        }
        catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
            return new ResponseEntity<>(new BaseResponse(exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

}
