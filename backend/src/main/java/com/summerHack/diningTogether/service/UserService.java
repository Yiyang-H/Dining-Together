package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.config.ApplicationProperties;
import com.summerHack.diningTogether.dto.*;
import com.summerHack.diningTogether.exceptions.UnAuthorizedUserAccessException;
import com.summerHack.diningTogether.exceptions.UserAlreadyExistException;
import com.summerHack.diningTogether.exceptions.UserCodeNotFoundException;
import com.summerHack.diningTogether.exceptions.UserNotFoundException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.model.UserDetails;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.UserCodeRepository;
import com.summerHack.diningTogether.repository.UserCodeRepositoryImpl;
import com.summerHack.diningTogether.repository.UserRepository;
import com.summerHack.diningTogether.utils.Base64Utils;

import com.summerHack.diningTogether.utils.EmailVerificationUtilsImpl;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
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
    private EmailVerificationUtilsImpl emailVerificationUtils;
    private UserCodeRepository userCodeRepository;
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
    @Transactional
    public User registerUser(RegisterInput input, String siteURL) throws UserAlreadyExistException, MessagingException, UserCodeNotFoundException {
        if (userRepository.findByUsername(input.getUsername()).isPresent()
            || userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }



        final User user = mapper.map(input, User.class);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setCurrency(properties.getDefaultCurrency());
        String randomCode = RandomString.make(64);
        UserCodeDTO userRegistrationCodeDTO =
                new UserCodeDTO(user.getId(), randomCode);
        user.setVerified(false);
        userRepository.save(user);
        userCodeRepository.save(userRegistrationCodeDTO);
        emailVerificationUtils.sendEmail(user, siteURL);
        return user;
    }

    private UserDTO userToDto(User user) {
        final UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.setAvatarBase64(Base64Utils.byteArrayToBase64(user.getAvatar()));
        return dto;
    }

    public boolean verify(String verificationCode) {
        if(userCodeRepository.findByCode(verificationCode).isPresent() == false)
            return false;
        UserCodeDTO userCode = userCodeRepository.findByCode(verificationCode).get();

        User user = userRepository.findById(userCode.getId()).get();
        userCodeRepository.delete(userCode.getId());
        user.setVerified(true);
        userRepository.save(user);
        return true;


    }

}
