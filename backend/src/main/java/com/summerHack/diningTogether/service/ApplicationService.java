package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.ApplicationDTO;
import com.summerHack.diningTogether.dto.FoodDTO;
import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.ApplicationNoFoundException;
import com.summerHack.diningTogether.model.*;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.summerHack.diningTogether.exceptions.UnimplementedException;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;


import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Service
@AllArgsConstructor
public class ApplicationService {



    private ApplicationRepository applicationRepository;
    private UserRepository userRepository;
    private FoodRepository foodRepository;
    private ModelMapper modelMapper;
    public Application approve(long foodId, long candidateId) throws Exception {
        Application applicationTOSave = findApplication(foodId,candidateId);
        applicationTOSave.setStatus(ApplicationStatus.ACCEPTED);
        return applicationRepository.save(applicationTOSave);


    }

    public Application reject(long foodId, long candidateId) throws Exception {
        Application applicationTOSave = findApplication(foodId,candidateId);
        applicationTOSave.setStatus(ApplicationStatus.DECLINED);
        return applicationRepository.save(applicationTOSave);
    }

    public List<UserDTO> getAllCandidates(long foodId){
        return applicationRepository.findAllCandidatesByFoodId(foodId).stream().map(
                user->modelMapper.map(user, UserDTO.class)
        ).collect(Collectors.toList());

    }

    private Application findApplication(long foodId, long candidateId) throws ApplicationNoFoundException {
        ApplicationId applicationId = getApplicationId(foodId, candidateId);
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            throw new ApplicationNoFoundException();
        } else {
            Application applicationToGive = applicationOptional.get();
            return applicationToGive;
        }
    }

    public Application update(long foodId, long userId) throws ApplicationNoFoundException {
        //Assume user and food exist
        ApplicationId applicationId = getApplicationId(foodId, userId);
        Application application = new Application(applicationId,ApplicationStatus.PENDING,
                new Timestamp(System.currentTimeMillis()));
        return applicationRepository.save(modelMapper.map(application, Application.class));
    }
    private ApplicationId getApplicationId(long foodId, long userId) throws ApplicationNoFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (userOptional.isEmpty() || foodOptional.isEmpty()) {
            throw new ApplicationNoFoundException();
        }

        User candidate = userOptional.get();
        Food food = foodOptional.get();
        return new ApplicationId(candidate, food);
    }
}
