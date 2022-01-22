package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserApplicationDTO {

    FoodDTO food;

    ApplicationStatus status;
}
