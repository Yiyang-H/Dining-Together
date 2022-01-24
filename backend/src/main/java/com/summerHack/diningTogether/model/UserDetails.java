package com.summerHack.diningTogether.model;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Value
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    String username;
    String password;

    Long id;

    public static UserDetails of(User user) {
        return new UserDetails(user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
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
