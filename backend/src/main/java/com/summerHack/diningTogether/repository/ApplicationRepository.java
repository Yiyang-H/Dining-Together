package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.ApplicationId;
import com.summerHack.diningTogether.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, ApplicationId> {
    @Query("select u from USER u INNER JOIN APPLICATION a where a.food_Id = ?1")
    List<User> findAllCandidatesByFoodId(Integer foodId);
}
