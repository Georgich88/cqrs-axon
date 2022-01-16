package com.georgeisaev.springbank.user.query.api.controller;

import com.georgeisaev.springbank.user.query.api.dto.UserSearchResponse;
import com.georgeisaev.springbank.user.query.api.query.FindAllUsersQuery;
import com.georgeisaev.springbank.user.query.api.query.FindUserByIdQuery;
import com.georgeisaev.springbank.user.query.api.query.SearchUsersQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping(path = "/api/v1/user-search")
public class UserQueryController {

    private static final String MSG_ERR_QUERY_REQUEST = "Error while processing query user request for id {}";
    public static final String MSG_ERR_ALL_QUERY_REQUEST = "Error while processing query user request";

    QueryGateway queryGateway;

    @GetMapping
    public ResponseEntity<UserSearchResponse> findAllUsers() {
        try {
            var query = new FindAllUsersQuery();
            var response = queryGateway
                    .query(query, ResponseTypes.instanceOf(UserSearchResponse.class)).join();
            if (isNull(response) || isNull(response.getUsers())) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(MSG_ERR_ALL_QUERY_REQUEST, e);
            return ResponseEntity.internalServerError().body(new UserSearchResponse(MSG_ERR_ALL_QUERY_REQUEST));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSearchResponse> findById(@PathVariable String id) {
        try {
            var query = new FindUserByIdQuery(id);
            var response = queryGateway
                    .query(query, ResponseTypes.instanceOf(UserSearchResponse.class)).join();
            if (isNull(response) || isNull(response.getUsers())) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormatter.format(MSG_ERR_QUERY_REQUEST, id).getMessage();
            log.error(safeErrorMessage, e);
            return ResponseEntity.internalServerError().body(new UserSearchResponse(safeErrorMessage));
        }
    }

    @GetMapping("/search/{filter}")
    public ResponseEntity<UserSearchResponse> search(@PathVariable String filter) {
        try {
            var query = new SearchUsersQuery(filter);
            var response = queryGateway
                    .query(query, ResponseTypes.instanceOf(UserSearchResponse.class)).join();
            if (isNull(response) || isNull(response.getUsers())) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormatter.format(MSG_ERR_QUERY_REQUEST, filter).getMessage();
            log.error(safeErrorMessage, e);
            return ResponseEntity.internalServerError().body(new UserSearchResponse(safeErrorMessage));
        }
    }

}
