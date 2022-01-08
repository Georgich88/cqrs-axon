package com.georgeisaev.springbank.user.cmd.api.aggregate;

import com.georgeisaev.springbank.user.cmd.api.command.RegisterUserCommand;
import com.georgeisaev.springbank.user.cmd.api.command.RemoveUserCommand;
import com.georgeisaev.springbank.user.cmd.api.command.UpdateUserCommand;
import com.georgeisaev.springbank.user.cmd.api.security.PasswordEncoder;
import com.georgeisaev.springbank.user.cmd.api.security.PasswordEncoderImpl;
import com.georgeisaev.springbank.usercore.event.UserRegisteredEvent;
import com.georgeisaev.springbank.usercore.event.UserRemovedEvent;
import com.georgeisaev.springbank.usercore.event.UserUpdatedEvent;
import com.georgeisaev.springbank.usercore.model.Account;
import com.georgeisaev.springbank.usercore.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Aggregate
public class UserAggregate {

    @TargetAggregateIdentifier
    String id;
    User user;
    final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        this();

        var newUser = command.getUser();
        newUser.setId(command.getId());
        fillUserPassword(newUser);

        var event = UserRegisteredEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build();
        AggregateLifecycle.apply(event);
    }

    private void fillUserPassword(User user) {
        final Account account = user.getAccount();
        String password = account.getPassword();
        String hashedPassword = passwordEncoder.hashPassword(password);
        account.setPassword(hashedPassword);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        fillUserPassword(updatedUser);

        var event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = UserRemovedEvent.builder()
                .id(command.getId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
