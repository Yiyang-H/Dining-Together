package com.summerHack.diningTogether.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FoodInput {
    @NotEmpty
    String title;

    @NotEmpty
    String description;

    String location;

    // TODO: Other fields
}
