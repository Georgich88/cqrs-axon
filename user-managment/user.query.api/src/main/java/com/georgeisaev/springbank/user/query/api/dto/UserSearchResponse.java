package com.georgeisaev.springbank.user.query.api.dto;

import com.georgeisaev.springbank.usercore.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSearchResponse {

    List<User> users;

    public UserSearchResponse(User user) {
        this.users = Collections.singletonList(user);
    }

}
