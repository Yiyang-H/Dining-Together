package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.config.ApplicationProperties;
import com.summerHack.diningTogether.dto.RegisterInput;
import com.summerHack.diningTogether.dto.UpdateUserInput;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UnAuthorizedUserAccessException;
import com.summerHack.diningTogether.exceptions.UserAlreadyExistException;
import com.summerHack.diningTogether.exceptions.UserNotFoundException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.model.UserDetails;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final ApplicationProperties properties;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private final SessionService sessionService;

    public UserDTO getProfile(long id) throws UserNotFoundException, UnAuthorizedUserAccessException {
        final User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        final User currentUser = sessionService.getOrThrowUnauthorized();

        if (!user.getId().equals(currentUser.getId())) {
            throw new UnAuthorizedUserAccessException();
        }

        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO update(long id, UpdateUserInput userInput) throws UserNotFoundException {

        final User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        modelMapper.map(userInput, user);
        userRepository.save(user);

        return modelMapper.map(user, UserDTO.class);

    }

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
        user.setCurrency(properties.getDefaultCurrency());

        return userRepository.save(user);
    }
}
