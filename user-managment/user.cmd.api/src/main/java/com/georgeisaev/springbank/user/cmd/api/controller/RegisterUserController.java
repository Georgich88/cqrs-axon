package com.georgeisaev.springbank.user.cmd.api.controller;

import com.georgeisaev.springbank.user.cmd.api.command.RegisterUserCommand;
import com.georgeisaev.springbank.user.cmd.api.dto.RegisterUserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping(path = "/api/v1/register-user")
public class RegisterUserController {

    private static final String MSG_ERR_REGISTER_REQUEST = "Error while processing register user request for id {}";
    private static final String MSG_INFO_USER_SUCCESSFULLY_REGISTERED = "User successfully registered";

    CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        try {
            command.setId(UUID.randomUUID().toString());
            commandGateway.sendAndWait(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(MSG_INFO_USER_SUCCESSFULLY_REGISTERED));
        } catch (Exception e) {
            String safeErrorMessage = MessageFormatter.format(MSG_ERR_REGISTER_REQUEST, command.getId()).getMessage();
            log.error(safeErrorMessage, e);
            return ResponseEntity.internalServerError().body(new RegisterUserResponse(safeErrorMessage));
        }
    }

}
