package com.georgeisaev.springbank.user.query.api.dto;

import com.georgeisaev.springbank.usercore.dto.BaseResponse;
import com.georgeisaev.springbank.usercore.model.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSearchResponse extends BaseResponse {

    List<User> users;

    public UserSearchResponse(User user) {
        this(null, user);
    }

    public UserSearchResponse(String message) {
        this(message, (User) null);
    }

    public UserSearchResponse(String message, User user) {
        super(message);
        this.users = Collections.singletonList(user);
    }

    public UserSearchResponse(List<User> users) {
        this(null, users);
    }

    public UserSearchResponse(String message, List<User> users) {
        super(message);
        this.users = new ArrayList<>(users);
    }

}
