package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.config.ApplicationProperties;
import com.summerHack.diningTogether.dto.RegisterInput;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UserAlreadyExistException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.model.UserDetails;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final ApplicationProperties properties;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public UserDTO getProfile(long id) {
        System.out.println(userRepository.findById(id).toString());
        return modelMapper.map(userRepository.findById(id).get(), UserDTO.class);
    }

    public User update(long id, User user) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new Exception("can not find such user");
        }
        User userToUpdate = userOptional.get();
        if (user.getAvatar() != null) {
            userToUpdate.setAvatar(user.getAvatar());
        }

        if (user.getUsername() != null) {
            userToUpdate.setUsername(user.getUsername());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getPhoneNumber() != null) {
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getSuburb() != null) {
            userToUpdate.setSuburb(user.getSuburb());
        }
        userRepository.save(userToUpdate);
        return userToUpdate;

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
