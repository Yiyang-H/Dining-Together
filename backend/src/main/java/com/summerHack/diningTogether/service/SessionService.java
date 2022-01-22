package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {
    private final UserRepository userRepository;

    public Optional<User> getCurrentUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = auth.getName();
        return userRepository.findByUsername(username);
    }

    public User getCurrentUserOrThrow() {
        return getCurrentUser().orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }
}
