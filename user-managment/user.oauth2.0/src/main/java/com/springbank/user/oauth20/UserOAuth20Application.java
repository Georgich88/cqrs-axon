package com.springbank.user.oauth20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class UserOAuth20Application {

    public static void main(String[] args) {
        SpringApplication.run(UserOAuth20Application.class, args);
    }

}
