package com.summerHack.diningTogether;

import com.summerHack.diningTogether.dto.FoodDTO;
import com.summerHack.diningTogether.dto.FoodInput;
import com.summerHack.diningTogether.model.Food;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ValidationException;
import java.util.Base64;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Dining Together API", version = "1.0"))
@ConfigurationPropertiesScan("com.summerHack.diningTogether.config")
public class DiningTogetherApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiningTogetherApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(FoodInput.class, Food.class)
            .addMapping(input -> {
                if (input.getPicture() == null) {
                    return null;
                } else {
                    final byte[] picture = Base64.getDecoder().decode(input.getPicture());
                    if (picture == null) {
                        throw new ValidationException("The base64 cannot be converted into binary "
                            + "file");
                    }
                    return picture;
                }
            }, Food::setPicture);


        modelMapper.typeMap(Food.class, FoodDTO.class)
            .addMapping(food -> {
                if (food.getPicture() == null) {
                    return null;
                } else {
                    return Base64.getEncoder().encodeToString(food.getPicture());
                }
            }, FoodDTO::setPicture);

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
