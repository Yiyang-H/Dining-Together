package com.summerHack.diningTogether.dto;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDTO {

    private Integer id;
    @NotNull
    private String username;
    private String password;
    @NotNull
    private Integer currency;
    @NotNull
    @Email
    private String email;
    private byte[] avatar;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String suburb;

    private List<String> foodNames;




    public static UserDTO of(User user) {

        final UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());

        return userDTO;
    }
}

