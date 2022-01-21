package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.FoodDTO;
import com.summerHack.diningTogether.dto.FoodInput;
import com.summerHack.diningTogether.exceptions.FoodNotFoundException;
import com.summerHack.diningTogether.exceptions.UnAuthorizedFoodModificationException;
import com.summerHack.diningTogether.exceptions.UserNotFoundException;
import com.summerHack.diningTogether.model.ApplicationStatus;
import com.summerHack.diningTogether.model.Category;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
import com.summerHack.diningTogether.repository.UserRepository;
import com.summerHack.diningTogether.utils.Base64Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public FoodDTO confirmFood(long id) throws FoodNotFoundException, UnAuthorizedFoodModificationException, UserNotFoundException {
        Food food = foodRepository.findById(id)
            .orElseThrow(FoodNotFoundException::new);

        User provider = food.getProvider();
        if(provider.getId() != sessionService.getCurrentUser().get().getId()){
            throw new UnAuthorizedFoodModificationException();
        }
        User consumer = applicationRepository
                .findByStatus(ApplicationStatus.ACCEPTED)
                .orElseThrow(UserNotFoundException::new)
                .getCandidate();
        provider.setCurrency(provider.getCurrency() + food.getPrice());
        consumer.setCurrency(consumer.getCurrency() - food.getPrice());
        userRepository.save(provider);
        userRepository.save(consumer);
        food.setCompleted(Boolean.TRUE);
        return foodToDto(food);
    }

    public List<FoodDTO> findAll(Optional<Category> category, Optional<Boolean> confirmed) {
        return foodRepository.findByParameters(category, confirmed)
            .stream()
            .map(this::foodToDto)
            .collect(Collectors.toList());
    }

    public FoodDTO addFood(FoodInput input) {
        final User user = sessionService.getOrThrowUnauthorized();

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
}


