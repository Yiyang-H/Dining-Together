package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.ApplicationStatus;
import lombok.Data;

@Data
public class ApplicationDTO {

    UserDTO candidate;

    ApplicationStatus status;

    String createdTime;
}
