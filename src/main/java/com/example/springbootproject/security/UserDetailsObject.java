package com.example.springbootproject.security;

import com.example.springbootproject.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsObject implements UserDetails {

    private User user;

    public UserDetailsObject(User user){
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getType().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
