package com.georgeisaev.springbank.usercore.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Size(min = 2, message = "Username must have a minimum of 2 characters")
    String username;

    @Size(min = 7, message = "Password must have a minimum of 2 characters")
    String password;

    @NotNull(message = "Specify at lease one user role")
    List<Role> roles;

}
