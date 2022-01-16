package com.georgeisaev.springbank.user.query.api.query;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchUsersQuery {

    String filter;

}
