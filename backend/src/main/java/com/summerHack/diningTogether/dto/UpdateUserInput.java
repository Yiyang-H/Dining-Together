package com.summerHack.diningTogether.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateUserInput extends UserDTO {

    @NotEmpty
    String phoneNumber;

    @NotEmpty
    String suburb;
}
