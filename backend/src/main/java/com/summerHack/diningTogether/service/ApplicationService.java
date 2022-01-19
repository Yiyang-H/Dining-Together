package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.*;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summerHack.diningTogether.exceptions.UnimplementedException;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
@Service
@AllArgsConstructor
public class ApplicationService {



    private ApplicationRepository applicationRepository;
    private UserRepository userRepository;
    private FoodRepository foodRepository;
    public Application approve(long foodId, long candidateId) throws Exception {
        Application applicationTOSave = findApplication(foodId,candidateId);
        applicationTOSave.setStatus(ApplicationStatus.ACCEPTED);
        return applicationTOSave;


    }

    public Application reject(long foodId, long candidateId) throws Exception {
        Application applicationTOSave = findApplication(foodId,candidateId);
        applicationTOSave.setStatus(ApplicationStatus.DECLINED);
        return applicationTOSave;
    }

    public List<User> getAllCandidates(long foodId){
        //return applicationRepository.findAllCandidatesByFoodId(foodId);
        return new ArrayList<>();
    }

    private Application findApplication(long foodId, long candidateId) throws Exception {
        Optional<User> userOptional = userRepository.findById(candidateId);
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (userOptional.isEmpty() || foodOptional.isEmpty()) {
            throw new Exception("can't find application");
        }

        User candidate = userOptional.get();
        Food food = foodOptional.get();
        ApplicationId applicationId = new ApplicationId(candidate, food);
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            throw new Exception("can't find application");
        } else {
            Application applicationToGive = applicationOptional.get();
            return applicationToGive;
        }
    }

}
