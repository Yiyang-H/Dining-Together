package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Category;
import com.summerHack.diningTogether.model.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("Select f from Food f where f.category = ?1")
    List<Food> findFoodByCategory(Category Category);



}
