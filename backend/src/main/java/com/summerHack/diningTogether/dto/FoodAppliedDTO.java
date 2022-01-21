package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FoodAppliedDTO {
    FoodDTO food;
    ApplicationStatus status;
}
