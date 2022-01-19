package com.georgeisaev.springbank.oauth20.repository;

import com.georgeisaev.springbank.usercore.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.constraints.Size;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByAccountUsername(@Size(min = 2, message = "Username must have a minimum of 2 characters") String username);

}
