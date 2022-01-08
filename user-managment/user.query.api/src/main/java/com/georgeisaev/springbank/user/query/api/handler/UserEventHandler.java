package com.georgeisaev.springbank.user.query.api.handler;

import com.georgeisaev.springbank.usercore.event.UserRegisteredEvent;
import com.georgeisaev.springbank.usercore.event.UserRemovedEvent;
import com.georgeisaev.springbank.usercore.event.UserUpdatedEvent;

public interface UserEventHandler {

    void on(UserRegisteredEvent event);

    void on(UserUpdatedEvent event);

    void on(UserRemovedEvent event);

}
