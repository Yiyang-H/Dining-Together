package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.config.ApplicationProperties;
import com.summerHack.diningTogether.dto.RegisterInput;
import com.summerHack.diningTogether.dto.UpdateUserInput;
import com.summerHack.diningTogether.dto.UserApplicationDTO;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UnAuthorizedUserAccessException;
import com.summerHack.diningTogether.exceptions.UserAlreadyExistException;
import com.summerHack.diningTogether.exceptions.UserNotFoundException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.model.UserDetails;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import com.summerHack.diningTogether.utils.Base64Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final ApplicationProperties properties;
    private final SessionService sessionService;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private ApplicationRepository applicationRepository;

    public UserDTO getProfile(long id) throws UserNotFoundException, UnAuthorizedUserAccessException {
        final User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        final User currentUser = sessionService.getCurrentUserOrThrow();

        if (!user.getId().equals(currentUser.getId())) {
            throw new UnAuthorizedUserAccessException();
        }

        return userToDto(user);
    }

    @Transactional
    public UserDTO update(long id, UpdateUserInput userInput) throws UserNotFoundException {

        final User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        modelMapper.map(userInput, user);
        user.setAvatar(Base64Utils.base64ToByteArray(userInput.getAvatarBase64()));

        return userToDto(user);

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

    private UserDTO userToDto(User user) {
        final UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.setAvatarBase64(Base64Utils.byteArrayToBase64(user.getAvatar()));
        return dto;
    }

    public List<UserApplicationDTO> getAllApplications(long id) {
        return applicationRepository
            .findByCandidate(userRepository.getById(id))
            .stream()
            .map(a -> modelMapper.map(a, UserApplicationDTO.class))
            .collect(Collectors.toList());
    }

    public List<FoodDTO> getAllFoodProvided(long id) throws UserNotFoundException, UnAuthorizedUserAccessException {
        final User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
        final User currentUser = sessionService.getOrThrowUnauthorized();

        if (!user.getId().equals(currentUser.getId())) {
            throw new UnAuthorizedUserAccessException();
        }
        return user
                .getFoods()
                .stream()
                .map(food -> modelMapper.map(food, FoodDTO.class))
                .collect(Collectors.toList());
    }
}
