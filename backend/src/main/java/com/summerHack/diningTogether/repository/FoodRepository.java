package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Food;
import org.springframework.data.repository.Repository;

public interface FoodRepository extends Repository<Food, Long> {
    Food save(Food food);
}
