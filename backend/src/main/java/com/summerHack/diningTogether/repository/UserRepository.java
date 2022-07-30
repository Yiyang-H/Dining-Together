package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);


    Optional<Object> findByPhoneNumber(String phoneNumber);
}
