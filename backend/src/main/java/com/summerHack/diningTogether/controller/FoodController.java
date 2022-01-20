package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.dto.FoodDTO;
import com.summerHack.diningTogether.dto.FoodInput;
import com.summerHack.diningTogether.exceptions.FoodNotFoundException;
import com.summerHack.diningTogether.model.Category;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/foods")
public class FoodController {

    private FoodService foodService;
    private ModelMapper modelMapper;

    @Operation(summary = "list all foods", description = "with or without category")
    @GetMapping("/")
    public List<FoodDTO> getAllFoods(
        @RequestParam Optional<Category> category) {

        if (category.isEmpty()) {
            return this.foodService.findAll();
        } else {

            return this.foodService.findByCategory(category.get());
        }
    }

    @Operation(summary = "show food by id")
    @ApiResponse(description = "Food gotten", responseCode = "200")
    @ApiResponse(description = "Food not found", responseCode = "404")
    @GetMapping("/{id}")

    public FoodDTO getFood(@PathVariable("id") long id)
        throws FoodNotFoundException {

        return this.foodService.getFoodById(id);
    }

    @Operation(summary = "add food to menu")
    @PostMapping("/")
    public FoodDTO addFood(@RequestBody FoodInput input) {
        final Food foodInput = modelMapper.map(input, Food.class);
        final Food food = this.foodService.addFood(foodInput);
        return modelMapper.map(food, FoodDTO.class);
    }

    @PutMapping("/{id}")
    @Operation(summary = "modify the information of a meal")

    public FoodDTO modifyFood(@PathVariable("id") long id, @RequestBody FoodInput food)
        throws FoodNotFoundException {

        return this.foodService.updateFood(id, food);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a meal")
    @ApiResponse(description = "Success", responseCode = "200")
    public void deleteFood(@PathVariable("id") long id) throws Exception {
        this.foodService.deleteFoodById(id);
    }

    @PutMapping("/{id}/confirm")
    @ApiResponse(description = "Success", responseCode = "200")
    public void confirmFood(@PathVariable("id") long id) throws Exception {
        this.foodService.confirmFood(id);
    }
}
