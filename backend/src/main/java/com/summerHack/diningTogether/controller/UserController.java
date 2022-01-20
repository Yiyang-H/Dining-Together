package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000/")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserDTO getProfile(@PathVariable long id) {
        return userService.getProfile(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateProfile(
        @PathVariable long id, @RequestBody UserDTO userDTO) throws Exception {

        final User userInput = modelMapper.map(userDTO, User.class);
        final User userOutput = userService.update(id, userInput);
        return modelMapper.map(userOutput, UserDTO.class);
    }

}
