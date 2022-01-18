package com.summerHack.diningTogether.dto;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Stream;

@Data
public class UserDTO {

    @NotNull
    private String username;

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

