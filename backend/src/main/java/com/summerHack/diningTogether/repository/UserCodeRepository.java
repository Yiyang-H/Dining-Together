package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.dto.UserCodeDTO;


import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;



public interface UserCodeRepository {
    boolean save(UserCodeDTO userCodeDTO);


    UserCodeDTO findById(Long id);
    Optional<UserCodeDTO> findByCode(String code);
    public boolean delete(long id);

    boolean update(Long id, UserCodeDTO user);
}

