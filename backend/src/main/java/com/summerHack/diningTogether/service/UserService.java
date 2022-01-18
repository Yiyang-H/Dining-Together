package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.config.BusinessConstants;

import com.summerHack.diningTogether.dto.RegisterInput;
import com.summerHack.diningTogether.exceptions.UserAlreadyExistException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.summerHack.diningTogether.model.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.summerHack.diningTogether.dto.UserDTO;
import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final BusinessConstants businessConstants;

    public  UserDTO getProfile(long id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new Exception("can not find such user");
        }
        return modelMapper.map(userOptional.get(), UserDTO.class);
    }

    public User update(long id, User user) throws Exception{
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new Exception("can not find such user");
        }
        User userToUpdate = userOptional.get();
        if(user.getAvatar() != null){
            userToUpdate.setAvatar(user.getAvatar());
        }
        if(user.getCurrency() != null){
            userToUpdate.setCurrency(user.getCurrency());
        }
        if(user.getFoods()!= null){
            userToUpdate.setFoods(user.getFoods());
        }
        if(user.getUsername()!=null){
            userToUpdate.setUsername(user.getUsername());
        }
        if(user.getSuburb()!= null){
            userToUpdate.setSuburb(user.getSuburb());
        }
        //Here need more
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
        user.setCurrency(businessConstants.getDefaultCurrency());

        return userRepository.save(user);
    }
}
