package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.ApplicationId;
import com.summerHack.diningTogether.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, ApplicationId> {
    @Query(value = "select u from User u INNER JOIN Application a where a.id.food.foodId = ?1")
    List<User> findAllCandidatesByFoodId(Integer foodId);
}
