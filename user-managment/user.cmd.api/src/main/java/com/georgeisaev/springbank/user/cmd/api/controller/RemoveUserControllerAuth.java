package com.georgeisaev.springbank.user.cmd.api.controller;

import com.georgeisaev.springbank.user.cmd.api.command.RemoveUserCommand;
import com.georgeisaev.springbank.user.cmd.api.security.BearerAuthSecuredRestController;
import com.georgeisaev.springbank.usercore.dto.BaseResponse;
import com.georgeisaev.springbank.user.cmd.api.dto.RegisterUserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping(path = "/api/v1/remove-user")
public class RemoveUserControllerAuth implements BearerAuthSecuredRestController {

    private static final String MSG_ERR_REMOVE_REQUEST = "Error while processing remove user request for id {}";
    private static final String MSG_INFO_USER_SUCCESSFULLY_REMOVED = "User successfully removed";

    CommandGateway commandGateway;

    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<BaseResponse> removeUser(@PathVariable String id) {
        RemoveUserCommand command = new RemoveUserCommand(id);
        try {
            commandGateway.sendAndWait(command);
            return ResponseEntity.ok(new BaseResponse(MSG_INFO_USER_SUCCESSFULLY_REMOVED));
        } catch (Exception e) {
            String safeErrorMessage = MessageFormatter.format(MSG_ERR_REMOVE_REQUEST, command.getId()).getMessage();
            log.error(safeErrorMessage, e);
            return ResponseEntity.internalServerError().body(new RegisterUserResponse(safeErrorMessage));
        }
    }

}
