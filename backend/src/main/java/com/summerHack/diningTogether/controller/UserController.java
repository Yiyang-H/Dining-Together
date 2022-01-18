package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UnimplementedException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserDTO getProfile(@PathVariable long id) {
        throw new UnimplementedException();
    }

    @PutMapping("/{id}")
    public UserDTO updateProfile(
            @PathVariable long id, @Valid @RequestBody  UserDTO userDTO) throws Exception {

        final User userInput = modelMapper.map(userDTO, User.class);
        final User userOutput = userService.update(id, userInput);
        return modelMapper.map(userOutput, UserDTO.class);
    }

}
