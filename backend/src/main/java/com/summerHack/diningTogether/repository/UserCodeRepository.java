package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.dto.UserCodeDTO;


import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserCodeRepository extends CrudRepository<UserCodeDTO, Long> {
    Optional<UserCodeDTO> findByCode(String code);
}
