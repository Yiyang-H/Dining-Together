package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.Category;
import com.summerHack.diningTogether.model.FoodType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FoodInput {
    @NotEmpty
    String title;

    @NotEmpty
    String description;

    @NotEmpty
    String location;

    @Schema(example = "2022-11-12 13:02:56.12345678")
    @NotEmpty
    String endTime;

    @NotNull
    FoodType foodType;

    @NotNull
    Category category;

    @Min(1)
    @NotNull
    int consumerNumber;

    @Schema(nullable = true, description = "The base64 of the picture, can be null")
    String picture;
}
