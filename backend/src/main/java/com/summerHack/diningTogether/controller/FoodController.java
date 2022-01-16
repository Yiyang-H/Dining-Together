package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import com.summerHack.diningTogether.service.FoodService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/food")
public class FoodController {
    @Autowired
    private FoodService foodService;


    @ApiOperation(value = "list all unconfirmed food", notes = "with or without category")
    @GetMapping("/")
    public List<FoodBrief> findByCategory(@RequestParam (required = false) String category){
        if(category == null){
            return this.foodService.findAllUnconfirmed();
        }
        return this.foodService.findUnconfirmedByCategory(category);
    }
    @ApiOperation(value = "show food by id")
    @GetMapping("/{id}")
    public Food getFood(@PathVariable("id") int id){
        return this.foodService.getFoodById(id);
    }
    @ApiOperation(value = "add food to menu")
    @PostMapping("/")
    public String saveFood(@RequestBody Food food){
        return this.foodService.saveFood(food);
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "modify the information of a meal")
    public Food modifyFood(@PathVariable("id") int id, @RequestBody Food food){
        return this.foodService.updateFood(id, food);
    }
    @DeleteMapping("/{id}")
    public Food deleteFood(@PathVariable("id") Integer id){
        return this.foodService.deleteById(id);
    }
    @PatchMapping("/{id}")
    @ApiOperation(value = "delete a meal")
    public Food confirmFood(@PathVariable("id") Integer id){
        return this.foodService.confirmFood(id);
    }




}
