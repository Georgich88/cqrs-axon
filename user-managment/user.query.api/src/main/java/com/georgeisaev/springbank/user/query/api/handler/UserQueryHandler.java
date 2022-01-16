package com.georgeisaev.springbank.user.query.api.handler;

import com.georgeisaev.springbank.user.query.api.dto.UserSearchResponse;
import com.georgeisaev.springbank.user.query.api.query.FindAllUsersQuery;
import com.georgeisaev.springbank.user.query.api.query.FindUserByIdQuery;
import com.georgeisaev.springbank.user.query.api.query.SearchUsersQuery;

public interface UserQueryHandler {

    UserSearchResponse findUserById(FindUserByIdQuery query);

    UserSearchResponse searchUsers(SearchUsersQuery query);

    UserSearchResponse findAllUsers(FindAllUsersQuery query);

}
