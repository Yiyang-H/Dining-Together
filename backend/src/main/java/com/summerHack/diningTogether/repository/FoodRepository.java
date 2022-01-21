package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>, FoodRepositoryCustom {


}
