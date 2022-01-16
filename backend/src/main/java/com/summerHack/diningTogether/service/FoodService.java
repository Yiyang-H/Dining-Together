package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import com.summerHack.diningTogether.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class FoodService {
    @Autowired
    private FoodRepository foodRepository;

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

    public Food updateFood(int id, Food food){

    }

    public abstract Food deleteById(Integer id);

    public abstract List<FoodBrief> findByCategory(String category);

    public abstract Food confirmFood(Integer id);

    public abstract List<FoodBrief> findAll();
}
