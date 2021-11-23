package com.bk.todoappbackend.common.security.jwtauth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    private final List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
    private Long id = 1L;
    private static final String ROLE_USER = "ROLE_USER_2";

    public JwtInMemoryUserDetailsService() {
        inMemoryUserList.add(new JwtUserDetails(id, "bk",
                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", ROLE_USER));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (findFirst.isEmpty()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();
    }

    public boolean addUserToAuthenticatedUsers(String username, String encryptedPassword) {
        this.id++;
        return inMemoryUserList.add(new JwtUserDetails(id, username,
                encryptedPassword, ROLE_USER));
    }


}













