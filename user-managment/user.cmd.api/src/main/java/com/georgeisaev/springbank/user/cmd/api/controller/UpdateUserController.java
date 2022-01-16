package com.georgeisaev.springbank.user.cmd.api.controller;

import com.georgeisaev.springbank.user.cmd.api.command.UpdateUserCommand;
import com.georgeisaev.springbank.user.cmd.api.dto.BaseResponse;
import com.georgeisaev.springbank.user.cmd.api.dto.RegisterUserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping(path = "/api/v1/update-user")
public class UpdateUserController {

    private static final String MSG_ERR_UPDATE_REQUEST = "Error while processing update user request for id {}";
    private static final String MSG_INFO_USER_SUCCESSFULLY_UPDATED = "User successfully updated";

    CommandGateway commandGateway;

    @PutMapping(path = "{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable String id,
                                                   @Valid @RequestBody UpdateUserCommand command) {
        try {
            command.setId(id);
            commandGateway.sendAndWait(command);
            return ResponseEntity.ok(new BaseResponse(MSG_INFO_USER_SUCCESSFULLY_UPDATED));
        } catch (Exception e) {
            String safeErrorMessage = MessageFormatter.format(MSG_ERR_UPDATE_REQUEST, command.getId()).getMessage();
            log.error(safeErrorMessage, e);
            return ResponseEntity.internalServerError().body(new RegisterUserResponse(safeErrorMessage));
        }
    }

}
