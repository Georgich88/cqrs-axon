package com.georgeisaev.springbank.usercore.event;

import com.georgeisaev.springbank.usercore.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisteredEvent {

    String id;
    User user;

}
