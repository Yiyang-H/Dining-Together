package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
