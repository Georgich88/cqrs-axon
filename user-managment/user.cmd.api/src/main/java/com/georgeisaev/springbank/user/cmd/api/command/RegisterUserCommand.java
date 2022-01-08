package com.georgeisaev.springbank.user.cmd.api.command;

import com.georgeisaev.springbank.usercore.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserCommand {

    @TargetAggregateIdentifier
    String id;

    @NotNull(message = "User details should be supplied")
    @Valid
    User user;


}
