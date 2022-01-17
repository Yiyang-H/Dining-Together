package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.*;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ApplicationService {


    private ApplicationRepository applicationRepository;
    private UserRepository userRepository;
    private FoodRepository foodRepository;
    public Application approve(Integer foodId, Integer candidateId) throws Exception {
        Application applicationTOSave = findApplication(foodId,candidateId);
        applicationTOSave.setStatus(ApplicationStatus.ACCEPTED);
        return applicationTOSave;


    }

    public Application reject(Integer foodId, Integer candidateId) throws Exception {
        Application applicationTOSave = findApplication(foodId,candidateId);
        applicationTOSave.setStatus(ApplicationStatus.DECLINED);
        return applicationTOSave;
    }

    public List<User> getAllCandidates(Integer foodId){
        return applicationRepository.findAllCandidatesByFoodId(foodId);
    }

    private Application findApplication(Integer foodId, Integer candidateId) throws Exception {
        Optional<User> userOptional = userRepository.findById(candidateId);
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if(userOptional.isEmpty() || foodOptional.isEmpty()){
            throw new Exception("can't find application");
        }

        User candidate = userOptional.get();
        Food food = foodOptional.get();
        ApplicationId applicationId = new ApplicationId(candidate, food);
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if(applicationOptional.isEmpty()){
            throw new Exception("can't find application");
        }
        else{
            Application applicationToGive = applicationOptional.get();
            return applicationToGive;
        }

    }
}
