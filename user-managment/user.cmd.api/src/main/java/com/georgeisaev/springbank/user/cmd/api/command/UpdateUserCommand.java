package com.georgeisaev.springbank.user.cmd.api.command;

import com.georgeisaev.springbank.usercore.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    String id;

    User user;

}
