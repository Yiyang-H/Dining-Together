package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.FoodType;
import lombok.Data;

/**
 * FoodDTO, only for output
 */
@Data
public class FoodDTO extends FoodInput {

    long foodId;

    UserDTO provider;

    String endTime;

    String createdTime;

    FoodType foodType;

    boolean completed;

    int consumerNumber;
}
