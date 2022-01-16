package com.summerHack.diningTogether.DTO;

import com.summerHack.diningTogether.model.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationStatusInput {
    ApplicationStatus status;
}
