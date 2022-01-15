package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.model.Profile;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable int id){
        return userService.getProfile(id);
    }
    @PutMapping("/update/{id}")
    public User updateProfile(@PathVariable int id, @RequestBody User user){
        return userService.update(id);
    }
}
