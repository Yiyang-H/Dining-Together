package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.Converter.ApplicationConverter;
import com.summerHack.diningTogether.DTO.ApplicationDTO;
import com.summerHack.diningTogether.DTO.UpdateApplicationStatusInput;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "application for food list")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/foods/{id}/applications")
public class ApplicationController {

    private ApplicationService applicationService;
    private ApplicationConverter applicationConverter;
    private ApplicationRepository applicationRepository;
    @PostMapping("/")
    @ApiOperation(value = "submit application")
    public Application submitApplication(
            @RequestBody @Valid ApplicationDTO applicationDTO) {
        return applicationRepository.save(applicationConverter.applicationDtoToApplication(applicationDTO));
    }

    @PatchMapping("/{candidateId}")
    @ApiOperation(value = "Update application status", notes = "approve or reject")
    public Application updateStatus(
        @PathVariable("id") Integer foodId,
        @PathVariable("candidateId") Integer candidateId,
        @RequestBody  UpdateApplicationStatusInput input) throws Exception {

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
    @ApiOperation(value = "Return all applications of the food")
    public List<User> getAllApplications(@PathVariable("id") Integer foodId) {
        return applicationService.getAllCandidates(foodId);
    }
}
