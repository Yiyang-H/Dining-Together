package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.Converter.ApplicationConverter;
import com.summerHack.diningTogether.dto.ApplicationDTO;
import com.summerHack.diningTogether.dto.UpdateApplicationStatusInput;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.dto.UserId;
import com.summerHack.diningTogether.exceptions.ApplicationNoFoundException;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin("http://localhost:3000/")
@Tag(name = "application", description = "application for food list")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/foods/{id}/applications")
public class ApplicationController {

    private ApplicationService applicationService;
    private ModelMapper modelMapper;
    private ApplicationConverter applicationConverter;
    private ApplicationRepository applicationRepository;


    @PostMapping("/userId")
    @Operation(summary = "submit application")
    public ApplicationDTO submitApplication(
        @PathVariable("id") long foodId, @RequestBody UserId userId) throws ApplicationNoFoundException {


        return modelMapper.map(applicationService.update(foodId, userId.getId()), ApplicationDTO.class);

    }

    @PatchMapping("/{candidateId}")
    @Operation(summary = "Update application status", description = "APPROVE or REJECTED")
    public ApplicationDTO updateStatus(
        @PathVariable("id") long foodId,
        @PathVariable("candidateId") long candidateId,
        @RequestBody UpdateApplicationStatusInput input) throws Exception {


        switch (input.getStatus()) {
            case ACCEPTED:
                return modelMapper.map(applicationService.approve(foodId, candidateId), ApplicationDTO.class);
            case DECLINED:
                return modelMapper.map(applicationService.reject(foodId, candidateId), ApplicationDTO.class);
            default:

                throw new RuntimeException("Message Not Recognized");
        }
    }

    @GetMapping("/")
    @Operation(summary = "Return all applications of the food")
    public List<UserDTO> getAllApplications(@PathVariable("id") long foodId) {

        return applicationService.getAllCandidates(foodId);
    }
}
