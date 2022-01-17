package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;

import java.util.List;

public abstract class FoodService {
    public abstract List<FoodBrief> getAllFood();

    public abstract Food getFoodById(long id);

    public abstract String saveFood(Food food);

    public abstract Food updateFood(long id, Food food);

    public abstract Food deleteById(long id);

    public abstract List<FoodBrief> findByCategory(String category);

    public abstract Food confirmFood(long id);

    public abstract List<FoodBrief> findAll();
}
