package com.spring.security.jwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String clientId) throws UsernameNotFoundException {

        // logic to get the user from the Database
        if ("asha".equals(clientId)) {
            return new User("asha", "krishna", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Invalid id : " +clientId);
        }

    }
}
