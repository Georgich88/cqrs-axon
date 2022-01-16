package com.georgeisaev.springbank.user.query.api.handler;

import com.georgeisaev.springbank.user.query.api.dto.UserSearchResponse;
import com.georgeisaev.springbank.user.query.api.query.FindAllUsersQuery;
import com.georgeisaev.springbank.user.query.api.query.FindUserByIdQuery;
import com.georgeisaev.springbank.user.query.api.query.SearchUsersQuery;
import com.georgeisaev.springbank.user.query.api.repository.UserRepository;
import com.georgeisaev.springbank.usercore.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@ProcessingGroup("user-group")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserQueryHandlerImpl implements UserQueryHandler {

    UserRepository userRepository;

    @QueryHandler
    @Override
    public UserSearchResponse findUserById(FindUserByIdQuery query) {
        Optional<User> userById = userRepository.findById(query.getId());
        return userById.stream()
                .filter(Objects::nonNull)
                .map(UserSearchResponse::new)
                .findAny()
                .orElse(null);
    }

    @QueryHandler
    @Override
    public UserSearchResponse searchUsers(SearchUsersQuery query) {
        return new UserSearchResponse(userRepository.findByFilterRegex(query.getFilter()));
    }

    @QueryHandler
    @Override
    public UserSearchResponse findAllUsers(FindAllUsersQuery query) {
        return new UserSearchResponse(userRepository.findAll());
    }

}
