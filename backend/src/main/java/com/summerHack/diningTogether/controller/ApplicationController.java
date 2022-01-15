package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.Converter.ApplicationConverter;
import com.summerHack.diningTogether.DTO.ApplicationDTO;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/food/{id}/applications")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationConverter applicationConverter;
    @PostMapping("/")
    public Application submitApplication(@PathVariable("id") int id,
                                         @RequestBody ApplicationDTO applicationDTO){
        return applicationService.save(applicationConverter.applicationDtoToApplication(applicationDTO));
    }
    @PatchMapping("/{applicationId}")
    public Application approveCandidate(@PathVariable("applicationId") Integer applicationId){
        return applicationService.approve(applicationId);
    }
    @PutMapping ("reject/{candidateId}/{foodId}")
    public Application rejectCandidate(@PathVariable("candidateId") Integer candidateId,
                                       @PathVariable("foodId") Integer foodId){
        return applicationService.reject(foodId, candidateId);
    }
    @GetMapping("/allCandidates/{foodId}")
    public List<User> getAllCandidates(@PathVariable("foodId") Integer foodId){
        return applicationService.getAllCandidates(foodId);
    }

}
