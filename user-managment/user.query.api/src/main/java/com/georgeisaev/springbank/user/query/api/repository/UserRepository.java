package com.georgeisaev.springbank.user.query.api.repository;

import com.georgeisaev.springbank.usercore.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
