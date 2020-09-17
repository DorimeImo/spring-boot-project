package com.example.springbootproject.security;

import com.example.springbootproject.model.user.User;
import com.example.springbootproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyDatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("loadUserByUsername() : {}", s);
        User user = (User)repository.findByName(s);

        if(user==null){
            log.info("error in if statement");
            throw new UsernameNotFoundException("User not found.");
        }
        log.info("loadUserByUsername() : {}", s);

        return new UserDetailsObject(user);
    }
}
