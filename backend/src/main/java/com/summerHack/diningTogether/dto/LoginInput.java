package com.summerHack.diningTogether.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginInput {

    @NotEmpty
    String username;

    @NotEmpty
    String password;
}
