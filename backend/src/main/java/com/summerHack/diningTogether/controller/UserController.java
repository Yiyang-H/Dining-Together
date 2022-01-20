package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.UpdateUserInput;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UnAuthorizedUserAccessException;
import com.summerHack.diningTogether.exceptions.UserNotFoundException;
import com.summerHack.diningTogether.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserDTO getProfile(@PathVariable long id) throws UserNotFoundException, UnAuthorizedUserAccessException {
        return userService.getProfile(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateProfile(
        @PathVariable long id, @RequestBody UpdateUserInput userInput) throws Exception {

        return userService.update(id, userInput);
    }
}
