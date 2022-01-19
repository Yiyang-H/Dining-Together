package com.summerHack.diningTogether.service;
import com.summerHack.diningTogether.dto.FoodDTO;
import com.summerHack.diningTogether.dto.FoodInput;
import com.summerHack.diningTogether.exceptions.FoodNotFoundException;
import com.summerHack.diningTogether.exceptions.UnimplementedException;

import com.summerHack.diningTogether.model.*;
import com.summerHack.diningTogether.repository.ApplicationRepository;
import com.summerHack.diningTogether.repository.FoodRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


import java.sql.Timestamp;
import java.time.Instant;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired

    private final SessionService sessionService;
    private ApplicationRepository applicationRepository;
    public FoodDTO getFoodById(long id) throws FoodNotFoundException {
        final Food food = foodRepository.findById(id)
                .orElseThrow(FoodNotFoundException::new);
        return modelMapper.map(food, FoodDTO.class);

    }




    public Food updateFood(long id, Food food) throws Exception {
        Food foodToUpdate = FoodById(id);
        Condition notNull = ctx -> ctx.getSource() != null;

        return food;


    }

    public Food deleteFoodById(long id) throws Exception {
          Food food = FoodById(id);
//        System.out.println(food.getProvider().getId());

        food.setApplications(new ArrayList<>());

        foodRepository.deleteById(id);
        return food;

    }

    public List<FoodDTO> findByCategory(Category category) {
        List<Food> foodList = foodRepository.findFoodByCategory(category);

        return foodList.stream().map(food -> modelMapper.map(food, FoodDTO.class)).collect(Collectors.toList());
    }


    public FoodDTO confirmFood(long id) throws Exception {
        Food food = FoodById(id);
        food.setCompleted(Boolean.TRUE);
        return modelMapper.map(food, FoodDTO.class);
    }

    public List<FoodDTO> findAll() {
        List<FoodDTO> foodBriefList = new ArrayList<>();
        Iterable<Food> foodIterable = foodRepository.findAll();
        for (Food food : foodIterable) {
            foodBriefList.add(modelMapper.map(food, FoodDTO.class));
        }
        return foodBriefList;
    }

    public Food FoodById(long id) throws Exception {
        Optional<Food> foodOptional = foodRepository.findById(id);
        if (foodOptional.isEmpty()) {
            throw new Exception("Can't find food");
        }
        Food food = foodOptional.get();
        return food;
    }


    public Food addFood (Food food){
            final User user = sessionService.getOrThrowUnauthorized();

            food.setProvider(user);
            // TODO: time zone?
            food.setCreatedTime(Timestamp.from(Instant.now()));
            food.setCompleted(false);
            // TODO:
            food.setEndTime(Timestamp.from(Instant.now()));
            food.setConsumerNumber(10);
            food.setLocation("Somewhere");
            food.setFoodType(FoodType.DINING_IN);

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


