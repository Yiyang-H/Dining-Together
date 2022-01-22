package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.FoodDTO;
import com.summerHack.diningTogether.dto.FoodInput;
import com.summerHack.diningTogether.exceptions.FoodNotFoundException;
import com.summerHack.diningTogether.exceptions.UnAuthorizedFoodAccessException;
import com.summerHack.diningTogether.model.*;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import com.summerHack.diningTogether.utils.Base64Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {

    private final SessionService sessionService;
    private FoodRepository foodRepository;
    private ModelMapper modelMapper;
    private ApplicationRepository applicationRepository;
    private UserRepository userRepository;

    public FoodDTO getFoodById(long id) throws FoodNotFoundException {
        final Food food = foodRepository.findById(id)
            .orElseThrow(FoodNotFoundException::new);
        return foodToDto(food);
    }

    public void deleteFoodById(long id) {
        foodRepository.deleteById(id);
    }

    @Transactional
    public FoodDTO confirmFood(long id) throws FoodNotFoundException, UnAuthorizedFoodAccessException {

        final Food food = foodRepository.findById(id)
            .orElseThrow(FoodNotFoundException::new);
        ensureAuthorizedAccess(food);

        final User provider = food.getProvider();

        applicationRepository
            .findAllByFoodIdAndStatus(id, ApplicationStatus.ACCEPTED)
            .stream()
            .map(Application::getCandidate)
            .forEach(consumer -> {
                provider.modifyCurrency(food.getPrice());
                consumer.modifyCurrency(-food.getPrice());
            });

        food.setCompleted(Boolean.TRUE);
        return foodToDto(food);
    }

    public List<FoodDTO> findAll(Optional<Category> category, Optional<Boolean> completed) {
        return foodRepository.findByParameters(category, completed)
            .stream()
            .map(this::foodToDto)
            .collect(Collectors.toList());
    }

    public FoodDTO addFood(FoodInput input) {
        final User user = sessionService.getCurrentUserOrThrow();

        final Food food = modelMapper.map(input, Food.class);
        food.setProvider(user);
        // TODO: time zone?
        food.setCreatedTime(Timestamp.from(Instant.now()));
        food.setCompleted(false);
        food.setPicture(Base64Utils.base64ToByteArray(input.getPictureBase64()));

        foodRepository.save(food);
        return foodToDto(food);
    }


    public FoodDTO updateFood(long id, FoodInput input) throws FoodNotFoundException {
        final Food food = foodRepository.findById(id)
            .orElseThrow(FoodNotFoundException::new);

        modelMapper.map(input, food);
        food.setPicture(Base64Utils.base64ToByteArray(input.getPictureBase64()));

        foodRepository.save(food);
        return foodToDto(food);
    }

    private FoodDTO foodToDto(Food food) {
        final FoodDTO dto = modelMapper.map(food, FoodDTO.class);
        dto.setPictureBase64(Base64Utils.byteArrayToBase64(food.getPicture()));
        return dto;
    }

    private void ensureAuthorizedAccess(Food food) throws UnAuthorizedFoodAccessException {
        final User provider = food.getProvider();
        final User currentUser = sessionService.getCurrentUserOrThrow();

        if (!provider.getId().equals(currentUser.getId())) {
            throw new UnAuthorizedFoodAccessException();
        }
    }
}


