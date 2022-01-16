package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;

import java.util.List;

public abstract class FoodService {
    public abstract List<FoodBrief> getAllFood();

    public abstract Food getFoodById(int id);

    public abstract String saveFood(Food food);

    public abstract Food updateFood(int id, Food food);

    public abstract Food deleteById(Integer id);

    public abstract List<FoodBrief> findUnconfirmedByCategory(String category);

    public abstract Food confirmFood(Integer id);

    public abstract List<FoodBrief> findAllUnconfirmed();
}
