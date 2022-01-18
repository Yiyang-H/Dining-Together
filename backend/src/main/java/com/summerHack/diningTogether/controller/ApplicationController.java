package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.converter.ApplicationConverter;
import com.summerHack.diningTogether.dto.ApplicationDTO;
import com.summerHack.diningTogether.dto.UpdateApplicationStatusInput;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "application", description = "application for food list")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/foods/{id}/applications")
public class ApplicationController {

    private ApplicationService applicationService;

    private ApplicationConverter applicationConverter;
    private ApplicationRepository applicationRepository;



    @PostMapping("/")
    @Operation(summary = "submit application")
    public Application submitApplication(
            @PathVariable("id") long foodId,
        @RequestBody @Valid ApplicationDTO applicationDTO) {
            return applicationRepository.save(applicationConverter.applicationDtoToApplication(applicationDTO));

    }

    @PatchMapping("/{candidateId}")
    @Operation(summary = "Update application status", description = "approve or reject")
    public Application updateStatus(
            @PathVariable("id") long foodId,
        @PathVariable("candidateId") long candidateId,
        @RequestBody UpdateApplicationStatusInput input) throws Exception {


        switch (input.getStatus()) {
            case ACCEPTED:
                return applicationService.approve(foodId, candidateId);
            case DECLINED:
                return applicationService.reject(foodId, candidateId);
            default:

                throw new RuntimeException("Message Not Recognized");
        }
    }

    @GetMapping("/")
    @Operation(summary = "Return all applications of the food")
    public List<User> getAllApplications(@PathVariable("id") long foodId) {
        return applicationService.getAllCandidates(foodId);
    }
}
