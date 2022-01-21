package com.summerHack.diningTogether.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * FoodDTO, only for output
 */
@Data
public class FoodDTO extends FoodInput {
    @NotNull
    long foodId;
    @NotNull
    UserDTO provider;

    @NotNull
    String createdTime;

    boolean completed;

}
