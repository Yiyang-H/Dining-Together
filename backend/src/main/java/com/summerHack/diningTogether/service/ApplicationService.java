package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.ApplicationNotFoundException;
import com.summerHack.diningTogether.model.*;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
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
        Application applicationTOSave = findApplication(foodId, candidateId);
        applicationTOSave.setStatus(ApplicationStatus.ACCEPTED);
        return applicationRepository.save(applicationTOSave);


    }

    public Application reject(long foodId, long candidateId) throws Exception {
        Application applicationTOSave = findApplication(foodId, candidateId);
        applicationTOSave.setStatus(ApplicationStatus.DECLINED);
        return applicationRepository.save(applicationTOSave);
    }

    public List<UserDTO> getAllCandidates(long foodId) {
        return applicationRepository.findAllCandidatesByFoodId(foodId).stream().map(
            user -> modelMapper.map(user, UserDTO.class)
        ).collect(Collectors.toList());

    }

    private Application findApplication(long foodId, long candidateId) throws ApplicationNotFoundException {
        ApplicationId applicationId = getApplicationId(foodId, candidateId);
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            throw new ApplicationNotFoundException();
        } else {
            Application applicationToGive = applicationOptional.get();
            return applicationToGive;
        }
    }

    public Application update(long foodId, long userId) throws ApplicationNotFoundException {
        //Assume user and food exist
        ApplicationId applicationId = getApplicationId(foodId, userId);
        Application application = new Application(applicationId, ApplicationStatus.PENDING,
            new Timestamp(System.currentTimeMillis()));
        return applicationRepository.save(modelMapper.map(application, Application.class));
    }

    private ApplicationId getApplicationId(long foodId, long userId) throws ApplicationNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        if (userOptional.isEmpty() || foodOptional.isEmpty()) {
            throw new ApplicationNotFoundException();
        }

        User candidate = userOptional.get();
        Food food = foodOptional.get();
        return new ApplicationId(candidate, food);
    }
}
