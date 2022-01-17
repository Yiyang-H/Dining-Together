package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.DTO.UserDTO;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserDTO getProfile(@PathVariable int id) {
        return userService.getProfile(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateProfile(
        @PathVariable int id, @Valid @RequestBody  UserDTO userDTO) throws Exception {

        final User userInput = modelMapper.map(userDTO, User.class);
        final User userOutput = userService.update(id, userInput);
        return modelMapper.map(userOutput, UserDTO.class);
    }
}
