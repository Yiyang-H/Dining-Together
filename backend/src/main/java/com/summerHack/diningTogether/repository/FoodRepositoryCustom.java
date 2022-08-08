package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Category;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.User;

import java.util.List;
import java.util.Optional;

public interface FoodRepositoryCustom {
    List<Food> findByParameters(Optional<Category> category, Optional<Boolean> confirmed, Optional<Long> distance, User user);
}
