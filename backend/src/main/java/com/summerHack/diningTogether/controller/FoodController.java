package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import com.summerHack.diningTogether.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private FoodService foodService;

    @Operation(summary = "list all foods", description = "with or without category")
    @GetMapping("/")
    public List<FoodBrief> getAllFoods(
        @RequestParam Optional<String> category) {

        if (category.isEmpty()) {
            return this.foodService.findAll();
        } else {
            return this.foodService.findByCategory(category.get());
        }
    }

    @Operation(summary = "show food by id")
    @GetMapping("/{id}")
    public Food getFood(@PathVariable("id") int id) {
        return this.foodService.getFoodById(id);
    }

    @Operation(summary = "add food to menu")
    @PostMapping("/")
    public String saveFood(@RequestBody Food food) {
        return this.foodService.saveFood(food);
    }

    @PutMapping("/{id}")
    @Operation(summary = "modify the information of a meal")
    public Food modifyFood(@PathVariable("id") int id, @RequestBody Food food) {
        return this.foodService.updateFood(id, food);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a meal")
    public Food deleteFood(@PathVariable("id") Integer id) {
        return this.foodService.deleteById(id);
    }

    @PutMapping("/{id}/confirm")
    public Food confirmFood(@PathVariable("id") Integer id) {
        return this.foodService.confirmFood(id);
    }
}
