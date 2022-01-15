package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.Converter.ApplicationConverter;
import com.summerHack.diningTogether.DTO.ApplicationDTO;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "application for certain food")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/food/{id}/applications")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationConverter applicationConverter;

    @PostMapping("/")
    @ApiOperation(value = "submit application")
    public Application submitApplication(@PathVariable("id") int id,
                                         @RequestBody ApplicationDTO applicationDTO){
        return applicationService.save(applicationConverter.applicationDtoToApplication(applicationDTO));
    }
    @PatchMapping("/{applicationId}")
    @ApiOperation(value = "deal with candidate", notes = "approve or reject")
    public Application approveCandidate(@PathVariable("applicationId") Integer applicationId, @RequestParam Method method){
        if(method == Method.APPROVE){
            return applicationService.approve(applicationId);
        }
        else {
            return applicationService.reject(applicationId);
        }


    }

    @GetMapping("/candidates")
    @ApiOperation(value = "waiting list")
    public List<User> getAllCandidates(@PathVariable("id") Integer foodId){
        return applicationService.getAllCandidates(foodId);
    }

}
