package com.summerHack.diningTogether.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegisterInput {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @Email
    private String email;

    @NotEmpty // TODO: regex of phone number
    private String phoneNumber;

    @Size(min = 4, max = 4)
    private String postcode;
}
