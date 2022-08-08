package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.config.ApplicationProperties;
import com.summerHack.diningTogether.dto.*;
import com.summerHack.diningTogether.exceptions.*;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.model.UserDetails;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.UserCodeRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import com.summerHack.diningTogether.utils.Base64Utils;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

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
    private EmailService emailService;
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
    public UserDTO registerUser(RegisterInput input, String siteURL) throws UserAlreadyExistException, MessagingException, UserCodeNotFoundException, UserNotFoundException, DuplicatePhoneNumber {
        if (userRepository.findByUsername(input.getUsername()).isPresent()
            || userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        if(userRepository.findByPhoneNumber(input.getPhoneNumber()).isPresent())
            throw new DuplicatePhoneNumber();



        User user = mapper.map(input, User.class);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setCurrency(properties.getDefaultCurrency());
        String randomCode = UUID.randomUUID().toString();
        user.setVerified(false);
        userRepository.save(user);

        user = userRepository
                .findByUsername(user.getUsername())
                .orElseThrow(UserNotFoundException::new);
        UserCodeDTO userRegistrationCodeDTO =
                new UserCodeDTO();
        userRegistrationCodeDTO.setUserId(user.getId());
        userRegistrationCodeDTO.setVerificationCode(randomCode);
        //expire after 5 minutes
        //userRegistrationCodeDTO.setTokenExpires(Instant.now().plus(5, ChronoUnit.MINUTES));
        userCodeRepository.save(userRegistrationCodeDTO);

        emailService.sendEmail(user, siteURL);
        return mapper.map(user, UserDTO.class);
    }

    private UserDTO userToDto(User user) {
        final UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.setAvatarBase64(Base64Utils.byteArrayToBase64(user.getAvatar()));
        return dto;
    }

    public boolean verify(String verificationCode) throws UserCodeNotFoundException, UserNotFoundException, TimeOutException {
        if(userCodeRepository.findByCode(verificationCode) == null)
            return false;
        UserCodeDTO userCode = userCodeRepository
                .findByCode(verificationCode);
        /*if(userCode.isTokenExpired() == true){
            userCodeRepository.delete(userCode.getUserId());
            throw new TimeOutException();
        }*/

        Optional<User> oUser = userRepository
                .findById(userCode.getUserId());
        if(oUser.isPresent() == false){
            userCodeRepository.delete(userCode.getUserId());
            throw new UserNotFoundException();
        }
        User user = oUser.get();

        userCodeRepository.delete(userCode.getUserId());
        user.setVerified(true);
        userRepository.save(user);
        return true;


    }

}
