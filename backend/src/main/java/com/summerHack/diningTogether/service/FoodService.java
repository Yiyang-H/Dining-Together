package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.exceptions.UnimplementedException;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import com.summerHack.diningTogether.model.FoodType;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Component
public class FoodService {

    private final SessionService sessionService;
    private final FoodRepository foodRepository;

    public List<FoodBrief> getAllFood() {
        throw new UnimplementedException();
    }

    public Food getFoodById(long id) {
        throw new UnimplementedException();
    }

    public Food addFood(Food food) {
        final User user = sessionService.getOrThrowUnauthorized();

        food.setProvider(user);
        // TODO: time zone?
        food.setCreatedTime(Timestamp.from(Instant.now()));
        food.setCompleted(false);
        // TODO:
        food.setEndTime(Timestamp.from(Instant.now()));
        food.setConsumerNumber(10);
        food.setLocation("Somewhere");
        food.setFoodType(FoodType.DINING_IN);

        return foodRepository.save(food);
    }

    public Food updateFood(long id, Food food) {
        throw new UnimplementedException();
    }

    public Food deleteById(long id) {
        throw new UnimplementedException();
    }

    public List<FoodBrief> findByCategory(String category) {
        throw new UnimplementedException();
    }

    public Food confirmFood(long id) {
        throw new UnimplementedException();
    }

    public List<FoodBrief> findAll() {
        throw new UnimplementedException();
    }
}
