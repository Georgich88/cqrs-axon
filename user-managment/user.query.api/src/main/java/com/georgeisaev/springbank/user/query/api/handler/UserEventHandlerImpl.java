package com.georgeisaev.springbank.user.query.api.handler;

import com.georgeisaev.springbank.user.query.api.repository.UserRepository;
import com.georgeisaev.springbank.usercore.event.UserRegisteredEvent;
import com.georgeisaev.springbank.usercore.event.UserRemovedEvent;
import com.georgeisaev.springbank.usercore.event.UserUpdatedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEventHandlerImpl implements UserEventHandler {

    UserRepository userRepository;

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }

}
