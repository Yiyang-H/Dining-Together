package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.exceptions.UnimplementedException;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodService {
    public List<FoodBrief> getAllFood() {
        throw new UnimplementedException();
    }

    public Food getFoodById(int id) {
        throw new UnimplementedException();
    }

    public String saveFood(Food food) {
        throw new UnimplementedException();
    }

    public Food updateFood(int id, Food food) {
        throw new UnimplementedException();
    }

    public Food deleteById(Integer id) {
        throw new UnimplementedException();
    }

    public List<FoodBrief> findByCategory(String category) {
        throw new UnimplementedException();
    }

    public Food confirmFood(Integer id) {
        throw new UnimplementedException();
    }

    public List<FoodBrief> findAll() {
        throw new UnimplementedException();
    }
}
