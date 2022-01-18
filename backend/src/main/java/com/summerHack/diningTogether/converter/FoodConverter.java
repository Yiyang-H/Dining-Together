package com.summerHack.diningTogether.converter;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class FoodConverter {
    @Autowired
    private ModelMapper modelMapper;
    public FoodBrief FoodToFoodBriefConverter(Food food){
        return modelMapper.map(food, FoodBrief.class);
    }
}
