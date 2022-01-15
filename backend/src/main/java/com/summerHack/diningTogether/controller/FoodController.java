package com.summerHack.diningTogether.controller;

import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import com.summerHack.diningTogether.service.FoodService;
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

    @GetMapping("/all")
    public List<FoodBrief> getFoodList(){
        return this.foodService.getAllFood();
    }

    @GetMapping("/{category}")
    public List<FoodBrief> findByCategory(@PathVariable("{category}") String category){
        return this.foodService.findByCategory(category);
    }
    @GetMapping("/{id}")
    public Food getFood(@PathVariable("id") int id){
        return this.foodService.getFoodById();
    }
    @PostMapping("/add")
    public String saveFood(@RequestBody Food food){
        return this.foodService.saveFood(food);
    }
    @PutMapping("/modify/{id}")
    public Food modifyFood(@PathVariable("id") int id, @RequestBody Food food){
        return this.foodService.updateFood(id, food);
    }
    @DeleteMapping("/delete/{id}")
    public Food deleteFood(@PathVariable("id") Integer id){
        return this.foodService.deleteById(id);
    }
    @PutMapping("/confirmed/{id}")
    public Food confirmFood(@PathVariable("id") Integer id){
        return this.foodService.confirmFood(id);
    }




}
