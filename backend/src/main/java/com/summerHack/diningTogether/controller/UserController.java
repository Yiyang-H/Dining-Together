package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.DTO.UserDTO;
import com.summerHack.diningTogether.model.Profile;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private ModelMapper modelMapper;
    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable int id){
        return userService.getProfile(id);
    }
    @PutMapping("/{id}")
    public User updateProfile(
            @PathVariable int id, @RequestBody UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        return userService.update(id, user);
    }
}
