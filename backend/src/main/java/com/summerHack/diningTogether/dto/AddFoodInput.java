package com.summerHack.diningTogether.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddFoodInput {
    @NotEmpty
    String title;

    @NotEmpty
    String description;

    // TODO: Other fields
}
