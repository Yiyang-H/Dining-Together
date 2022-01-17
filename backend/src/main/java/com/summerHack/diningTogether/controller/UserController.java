package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.UnimplementedException;
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
    public UserDTO getProfile(@PathVariable int id) {
        throw new UnimplementedException();
    }

    @PutMapping("/{id}")
    public UserDTO updateProfile(
        @PathVariable int id, @RequestBody UserDTO userDTO) {
        throw new UnimplementedException();
    }
}
