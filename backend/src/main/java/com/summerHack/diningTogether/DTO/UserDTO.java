package com.summerHack.diningTogether.DTO;

import com.summerHack.diningTogether.model.Food;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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


    private List<Food> foods;
}