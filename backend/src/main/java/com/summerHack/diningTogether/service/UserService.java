package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.config.BusinessConstants;
import com.summerHack.diningTogether.dto.RegisterInput;
import com.summerHack.diningTogether.exceptions.UserAlreadyExistException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.model.UserDetails;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final BusinessConstants businessConstants;

    public UserDetails getUserDetailsByUsername(String username) throws UsernameNotFoundException {
        return UserDetails.of(userRepository
            .findByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException(
                    format("User: %s, not found", username)
                )
            ));
    }

    /**
     * Crete a new user entity from register input.
     *
     * @param input
     * @return
     * @throws UserAlreadyExistException User with the username or email exist
     */
    public User registerUser(RegisterInput input) throws UserAlreadyExistException {
        if (userRepository.findByUsername(input.getUsername()).isPresent()
            || userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        final User user = mapper.map(input, User.class);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setCurrency(businessConstants.getDefaultCurrency());

        return userRepository.save(user);
    }
}
