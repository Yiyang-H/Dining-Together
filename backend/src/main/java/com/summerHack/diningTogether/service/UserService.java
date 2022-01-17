package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.DTO.UserDTO;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    public UserDTO getProfile(int id){
        return modelMapper.map(userRepository.findById(id), UserDTO.class);
    }

    public User update(int id, User user) throws Exception{
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
        //Remaining part of userToupdate
        return userToUpdate;

    }

}
