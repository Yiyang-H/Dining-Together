package com.summerHack.diningTogether.Converter;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import org.springframework.context.annotation.Bean;

@Bean
public abstract class FoodConverter {
    public abstract FoodBrief FoodToFoodBriefConverter(Food food);
}
