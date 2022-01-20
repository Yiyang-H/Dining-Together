package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.exceptions.ApplicationNotFoundException;
import com.summerHack.diningTogether.exceptions.FoodNotFoundException;
import com.summerHack.diningTogether.exceptions.UnAuthorizedApplicationAccessException;
import com.summerHack.diningTogether.exceptions.UserNotFoundException;
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
import java.util.stream.Collectors;

@Validated
@Service
@AllArgsConstructor
public class ApplicationService {

    private ApplicationRepository applicationRepository;
    private UserRepository userRepository;
    private FoodRepository foodRepository;

    private ModelMapper modelMapper;

    private final SessionService sessionService;

    public Application updateApplicationStatus(long foodId, long candidateId, ApplicationStatus status)
        throws UserNotFoundException, ApplicationNotFoundException, FoodNotFoundException,
        UnAuthorizedApplicationAccessException {

        final ApplicationId applicationId = checkAndBuildApplicationId(foodId, candidateId);
        ensureApplicationPermission(applicationId);

        final Application applicationTOSave = applicationRepository.findById(applicationId)
            .orElseThrow(ApplicationNotFoundException::new);
        applicationTOSave.setStatus(status);

        return applicationRepository.save(applicationTOSave);
    }

    public List<UserDTO> getAllCandidates(long foodId) {
        return applicationRepository.findAllCandidatesByFoodId(foodId)
            .stream()
            .map(user -> modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList());

    }

    public Application createApplication(long foodId, long userId)
        throws UserNotFoundException, FoodNotFoundException {
        final ApplicationId applicationId = checkAndBuildApplicationId(foodId, userId);

        final Application application = new Application(applicationId, ApplicationStatus.PENDING,
            new Timestamp(System.currentTimeMillis()));
        final Application applicationResult = modelMapper.map(application, Application.class);

        return applicationRepository.save(applicationResult);
    }

    private ApplicationId checkAndBuildApplicationId(long foodId, long userId)
        throws FoodNotFoundException, UserNotFoundException {

        final User candidate = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        final Food food = foodRepository.findById(foodId).orElseThrow(FoodNotFoundException::new);
        return new ApplicationId(candidate, food);
    }

    /**
     * Check if current user have permission to modify the application, if not, throw an exception
     *
     * @param applicationId
     */
    private void ensureApplicationPermission(ApplicationId applicationId)
        throws UnAuthorizedApplicationAccessException {

        final Long userId = sessionService.getOrThrowUnauthorized().getId();

        if (!applicationId.getConsumer().getId().equals(userId)) {
            throw new UnAuthorizedApplicationAccessException();
        }
    }
}
