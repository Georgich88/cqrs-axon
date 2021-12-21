package com.georgeisaev.springbank.user.cmd.api.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RemoveUserCommand {

    @TargetAggregateIdentifier
    String id;

}
