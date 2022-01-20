package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.ApplicationDTO;
import com.summerHack.diningTogether.dto.ApplicationInput;
import com.summerHack.diningTogether.dto.UpdateApplicationStatusInput;
import com.summerHack.diningTogether.exceptions.*;
import com.summerHack.diningTogether.model.ApplicationStatus;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.service.ApplicationService;
import com.summerHack.diningTogether.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@Validated
@Tag(name = "application", description = "application for food list")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/foods/{foodId}/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ModelMapper modelMapper;
    private final ApplicationRepository applicationRepository;
    private final SessionService sessionService;

    @PostMapping("/{candidateId}")
    @Operation(summary = "submit application")
    public ApplicationDTO submitApplication(
        @PathVariable("foodId") long foodId, @PathVariable("candidateId") long candidateId,
        @RequestBody ApplicationInput input)
        throws UserNotFoundException, FoodNotFoundException, ApplicationAlreadyExistException {

        return applicationService.createApplication(foodId, candidateId, input);
    }

    @PatchMapping("/{candidateId}")
    @Operation(summary = "Update application status", description = "APPROVE or REJECTED")
    public ApplicationDTO updateStatus(
        @PathVariable("foodId") long foodId,
        @PathVariable("candidateId") long candidateId,
        @RequestBody UpdateApplicationStatusInput input) throws ApplicationNotFoundException,
        UnAuthorizedApplicationAccessException {

        if (!input.getStatus().equals(ApplicationStatus.ACCEPTED)
            && !input.getStatus().equals(ApplicationStatus.DECLINED)) {
            throw new ValidationException("Only ACCEPTED and DECLINED are accepted.");
        }

        return applicationService.updateApplicationStatus(foodId, candidateId, input.getStatus());
    }

    @GetMapping("/")
    @Operation(summary = "Return all applications of the food")
    public List<ApplicationDTO> getAllApplications(@PathVariable("foodId") long foodId) {

        return applicationService.getAllApplicationsByFoodId(foodId);
    }
}
