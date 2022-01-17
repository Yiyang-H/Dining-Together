package com.summerHack.diningTogether.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.summerHack.diningTogether.Converter.FoodConverter;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import com.summerHack.diningTogether.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private FoodConverter foodConverter;
    public Food getFoodById(int id) throws Exception {
        Optional<Food> foodOptional = foodRepository.findById(id);
        if(foodOptional.isEmpty()){
            throw new Exception("Can't find food");
        }
        Food food = foodOptional.get();
        return food;
    }

    public String saveFood(Food food){
        foodRepository.save(food);
        return "hello world";
    }

    public Food updateFood(int id, Food food) throws Exception {
        Food foodToUpdate = getFoodById(id);
        Condition notNull = ctx -> ctx.getSource() != null;
        
        return food;


    }

    public Food deleteById(Integer id) throws Exception {
        Food food = getFoodById(id);
        foodRepository.deleteById(id);
        return food;

    }

    public List<FoodBrief> findByCategory(String category){
        List<Food> foodList = foodRepository.findFoodByCategory(category);

        return foodList.stream().map(food -> foodConverter.FoodToFoodBriefConverter(food)).collect(Collectors.toList());
    }

    public Food confirmFood(Integer id) throws Exception {
        Food food = getFoodById(id);
        food.setCompleted(Boolean.TRUE);
        return food;
    }

    public List<FoodBrief> findAll(){
        List<FoodBrief> foodBriefList = new ArrayList<>();
        Iterable<Food> foodIterable = foodRepository.findAll();
        for(Food food :foodIterable){
            foodBriefList.add(foodConverter.FoodToFoodBriefConverter(food));
        }
        return foodBriefList;
    }

}
