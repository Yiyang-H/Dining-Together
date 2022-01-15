package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    @PostMapping("/submit")
    public Application submitApplication(@RequestBody Application application){
        return applicationService.save(application);
    }
    @PutMapping("/approve/{candidateId}/{foodId}")
    public Application approveCandidate(@PathVariable("candidateId") Integer candidateId,
                                        @PathVariable("foodId") Integer foodId){
        return applicationService.approve(foodId, candidateId);
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
