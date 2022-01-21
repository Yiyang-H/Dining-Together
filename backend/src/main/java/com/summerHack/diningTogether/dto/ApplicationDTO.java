package com.summerHack.diningTogether.dto;

import com.summerHack.diningTogether.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    // TODO: replace UserDTO with UserPublicDto (remove some fields like currency)
    UserDTO candidate;

    ApplicationStatus status;

    String createdTime;

}
