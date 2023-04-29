package chiba.dev.api.controllers;

import chiba.dev.CQRSCORE.dtos.reponses.BaseResponse;
import chiba.dev.CQRSCORE.infrastructure.dispatchers.CommandDispatcher;
import chiba.dev.commands.RestoreDbCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/restore-db")
@Slf4j
public class RestoreDbController {

    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> restoreDb(@RequestBody RestoreDbCommand command){
        try{
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("Database was restored"), HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
            return new ResponseEntity<>(new BaseResponse(exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
