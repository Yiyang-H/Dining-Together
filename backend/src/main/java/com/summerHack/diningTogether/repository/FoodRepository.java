package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Integer> {
    @Query("Select f from FOOD f where f.category = ?1")
    List<Food> findFoodByCategory(String Category);
}
