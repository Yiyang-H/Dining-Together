package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Integer> {
}
