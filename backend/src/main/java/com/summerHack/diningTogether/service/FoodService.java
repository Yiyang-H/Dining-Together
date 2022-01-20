package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.FoodDTO;
import com.summerHack.diningTogether.dto.FoodInput;
import com.summerHack.diningTogether.exceptions.FoodNotFoundException;
import com.summerHack.diningTogether.model.Category;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodType;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
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

    public FoodDTO getFoodById(long id) throws FoodNotFoundException {
        final Food food = foodRepository.findById(id)
            .orElseThrow(FoodNotFoundException::new);
        return modelMapper.map(food, FoodDTO.class);

    }

    public void deleteFoodById(long id) {
        foodRepository.deleteById(id);
    }

    public FoodDTO confirmFood(long id) throws FoodNotFoundException {
        Food food = foodRepository.findById(id)
            .orElseThrow(FoodNotFoundException::new);
        food.setCompleted(Boolean.TRUE);
        return modelMapper.map(food, FoodDTO.class);
    }

    public List<FoodDTO> findAll(Optional<Category> category, Optional<Boolean> confirmed) {
        return foodRepository.findByParameters(category, confirmed)
            .stream()
            .map(e -> modelMapper.map(e, FoodDTO.class))
            .collect(Collectors.toList());
    }

    public Food addFood(Food food) {
        final User user = sessionService.getOrThrowUnauthorized();

        food.setProvider(user);
        // TODO: time zone?
        food.setCreatedTime(Timestamp.from(Instant.now()));
        food.setCompleted(false);

        return foodRepository.save(food);
    }


    public FoodDTO updateFood(long id, FoodInput input) throws FoodNotFoundException {
        final Food food = foodRepository.findById(id)
            .orElseThrow(FoodNotFoundException::new);

        modelMapper.map(input, food);

        foodRepository.save(food);

        return modelMapper.map(food, FoodDTO.class);
    }
}


