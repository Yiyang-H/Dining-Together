package com.summerHack.diningTogether.Config;

import com.summerHack.diningTogether.converter.ApplicationConverter;
import com.summerHack.diningTogether.converter.FoodConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiningTogetherApplicationConfig {
    @Bean
    public ApplicationConverter applicationConverter() {
        return new ApplicationConverter();
    }

    @Bean
    public FoodConverter foodConverter() {
        return new FoodConverter();
    }
}
