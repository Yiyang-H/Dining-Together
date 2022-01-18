package com.summerHack.diningTogether.converter;

import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserConverter {
    @Autowired
    private UserRepository userRepository;
    public UserDTO userToUserDTO(User user){

//        UserDTO userDTO = new UserDTO(
//                user.getId(),
//                user.getUsername(),
//                user.getCurrency(),
//                user.getEmail(),
//                user.getAvatar(),
//                user.getPhoneNumber(),
//                user.getSuburb(),
//                user.getFoods().stream()
//                        .map(food->food.getTitle())
//                        .collect(Collectors.toList()));
//        return userDTO;
//    }
//    public User userDTOToUser(UserDTO userDTO){
//
//                User user = new User(
//                        userDTO.getId(),
//                //Assume the account already exist
//                userDTO.getUsername(),
//                userRepository.findById(userDTO.getId()).get().getPassword(),
//                        userDTO.getCurrency(),
//                        userDTO.getEmail(),
//                        userDTO.getAvatar(),
//                        userDTO.getPhoneNumber(),
//                        userDTO.getSuburb(),
//                        //Here need change
//                        userRepository.findById(userDTO.getId()).get().getFoods());
//
//                return userRepository.findById(userDTO.getId()).get();
//


    }

}
