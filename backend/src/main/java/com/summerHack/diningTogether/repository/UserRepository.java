package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.User;

import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User save(User user);

}
