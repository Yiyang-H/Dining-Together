package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.FoodType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
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

    String endTime;
    @NotNull
    String createdTime;
    @NotNull
    FoodType foodType;

    boolean completed;

    int consumerNumber;
}
