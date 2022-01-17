package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.ApplicationDTO;
import com.summerHack.diningTogether.dto.UpdateApplicationStatusInput;
import com.summerHack.diningTogether.exceptions.UnimplementedException;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "application", description = "application for food list")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/foods/{id}/applications")
public class ApplicationController {

    private ApplicationService applicationService;

    @PostMapping("/")
    @Operation(summary = "submit application")
    public Application submitApplication(
        @PathVariable("id") int foodId,
        @RequestBody ApplicationDTO applicationDTO) {
        throw new UnimplementedException();
    }

    @PatchMapping("/{candidateId}")
    @Operation(summary = "Update application status", description = "approve or reject")
    public Application updateStatus(
        @PathVariable("id") long foodId,
        @PathVariable("candidateId") long candidateId,
        @RequestBody UpdateApplicationStatusInput input) {

        switch (input.getStatus()) {
            case ACCEPTED:
                return applicationService.approve(foodId, candidateId);
            case DECLINED:
                return applicationService.reject(foodId, candidateId);
            default:
                // TODO: a better response message
                throw new RuntimeException("Only ACCEPTED and DECLINED is acceptable");
        }
    }

    @GetMapping("/")
    @Operation(summary = "Return all applications of the food")
    public List<User> getAllApplications(@PathVariable("id") long foodId) {
        return applicationService.getAllCandidates(foodId);
    }
}
