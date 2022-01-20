package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.ApplicationId;
import com.summerHack.diningTogether.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {
    @Query(value = "select u from User u INNER JOIN Application a on u.id = a.id.consumer where a.id.food.foodId = ?1")
    List<User> findAllCandidatesByFoodId(long foodId);


}
