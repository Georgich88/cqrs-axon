package com.georgeisaev.springbank.oauth20.service;

import com.georgeisaev.springbank.usercore.model.User;
import com.georgeisaev.springbank.oauth20.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {

    public static final String MSG_ERR_USER_NOT_FOUND = "Incorrect Username / Password supplied";
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByAccountUsername = userRepository.findByAccountUsername(username);
        var user = userByAccountUsername.orElseThrow(() -> new UsernameNotFoundException(MSG_ERR_USER_NOT_FOUND));
        var account = user.getAccount();
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(account.getPassword())
                .authorities(account.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .build();
    }

}
