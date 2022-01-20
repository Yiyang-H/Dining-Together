package com.summerHack.diningTogether.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
