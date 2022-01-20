package com.summerHack.diningTogether.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateUserInput {

    @NotEmpty
    String phoneNumber;

    @NotEmpty
    String suburb;

    @Schema(nullable = true, description = "The base64 of the avatar, can be null")
    String avatarBase64;
}
