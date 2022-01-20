package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.ApplicationDTO;
import com.summerHack.diningTogether.exceptions.*;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.ApplicationStatus;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.Instant;
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

    @Transactional
    public ApplicationDTO updateApplicationStatus(long foodId, long candidateId, ApplicationStatus status)
        throws ApplicationNotFoundException,
        UnAuthorizedApplicationAccessException {

        final Application application = applicationRepository
            .findByFoodIdAndCandidateId(foodId, candidateId)
            .orElseThrow(ApplicationNotFoundException::new);
        ensureApplicationPermission(application);

        application.setStatus(status);

        return modelMapper.map(application, ApplicationDTO.class);
    }

    public List<ApplicationDTO> getAllApplicationsByFoodId(long foodId) {
        return applicationRepository
            .findAllByFoodId(foodId)
            .stream()
            .map(a -> modelMapper.map(a, ApplicationDTO.class))
            .collect(Collectors.toList());
    }

    @Transactional
    public ApplicationDTO createApplication(long foodId, long candidateId)
        throws UserNotFoundException, FoodNotFoundException, ApplicationAlreadyExistException {

        if (applicationRepository.existByFoodIdAndCandidateId(foodId, candidateId)) {
            throw new ApplicationAlreadyExistException();
        }

        final User candidate = userRepository.findById(candidateId).orElseThrow(UserNotFoundException::new);
        final Food food = foodRepository.findById(foodId).orElseThrow(FoodNotFoundException::new);

        final Application application = new Application();
        application.setFood(food);
        application.setCandidate(candidate);
        application.setStatus(ApplicationStatus.PENDING);
        application.setCreatedTime(Timestamp.from(Instant.now()));

        applicationRepository.save(application);
        assert application.getId() != null;

        return modelMapper.map(application, ApplicationDTO.class);
    }

    /**
     * Check if current user have permission to modify the application, if not, throw an exception
     */
    private void ensureApplicationPermission(Application application)
        throws UnAuthorizedApplicationAccessException {

        final Long userId = sessionService.getOrThrowUnauthorized().getId();

        if (!application.getFood().getProvider().getId().equals(userId)) {
            throw new UnAuthorizedApplicationAccessException();
        }
    }
}
