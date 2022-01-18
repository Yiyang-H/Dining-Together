package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.User;
import lombok.Data;

@Data
public class UserDTO {

    long userId;

    String username;

    public static UserDTO of(User user) {

        final UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());

        return userDTO;
    }
}
