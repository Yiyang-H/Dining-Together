package com.summerHack.diningTogether.Converter;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;


public class FoodConverter {
    private ModelMapper modelMapper;
    public FoodBrief FoodToFoodBriefConverter(Food food){
        return modelMapper.map(food, FoodBrief.class);
    }
}
