package com.georgeisaev.springbank.user.query.api;

import com.georgeisaev.springbank.usercore.event.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class, args);
    }

}
