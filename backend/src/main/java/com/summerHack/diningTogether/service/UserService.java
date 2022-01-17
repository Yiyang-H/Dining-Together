package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.UserDetails;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.lang.String.format;

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDetails getUserDetailsByUsername(String username) throws UsernameNotFoundException {
        return UserDetails.of(userRepository
            .findByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException(
                    format("User: %s, not found", username)
                )
            ));
    }
}
