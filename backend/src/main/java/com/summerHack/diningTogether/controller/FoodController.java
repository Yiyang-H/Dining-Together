package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import com.summerHack.diningTogether.service.FoodService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private FoodService foodService;

    @ApiOperation(value = "list all foods", notes = "with or without category")
    @GetMapping("/")
    public List<FoodBrief> getAllFoods(
        @RequestParam Optional<String> category) {

        if (category.isEmpty()) {
            return this.foodService.findAll();
        } else {
            return this.foodService.findByCategory(category.get());
        }
    }

    @ApiOperation(value = "show food by id")
    @GetMapping("/{id}")
    public FoodBrief getFood(@PathVariable("id") int id) throws Exception {

        return this.foodService.getFoodById(id);
    }

    @ApiOperation(value = "add food to menu")
    @PostMapping("/")
    public String saveFood(@RequestBody Food food) {
        return this.foodService.saveFood(food);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "modify the information of a meal")
    public Food modifyFood(@PathVariable("id") int id, @RequestBody Food food) throws Exception {
        return this.foodService.updateFood(id, food);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete a meal")
    public Food deleteFood(@PathVariable("id") Integer id) throws Exception {
        return this.foodService.deleteById(id);
    }

    @PutMapping("/{id}/confirm")
    public Food confirmFood(@PathVariable("id") Integer id) throws Exception {
        return this.foodService.confirmFood(id);
    }
}
