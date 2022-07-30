package com.summerHack.diningTogether.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO extends UpdateUserInput {

    @NotEmpty
    Long id;

    @NotEmpty
    String username;

    Integer currency;

    @Email
    String email;
}
