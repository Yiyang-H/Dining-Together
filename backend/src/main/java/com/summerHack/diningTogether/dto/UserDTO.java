package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    @NotEmpty
    Long id;
    @NotEmpty
    String username;
    Integer currency;
    @Email
    String email;
    byte[] avatar;
    @NotEmpty
    String phoneNumber;
    @NotEmpty
    String suburb;

    public static UserDTO of(User user) {

        final UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());

        return userDTO;
    }
}
