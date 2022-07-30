package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.dto.UserCodeDTO;
import com.summerHack.diningTogether.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserCodeRepositoryImpl implements UserCodeRepository{
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "diningTogether";
    @Override
    public boolean save(UserCodeDTO userCodeDTO) {
        try {
            redisTemplate.opsForHash().put(KEY, Long.toString(userCodeDTO.getId()), userCodeDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserCodeDTO findById(Long id) {
        UserDTO user;
        return (UserCodeDTO)redisTemplate.opsForHash().get(KEY,Long.toString(id));

    }

    @Override
    public Optional<UserCodeDTO> findByCode(String code) {
        return Optional.empty();
    }

    @Override
    public boolean update(Long id, UserCodeDTO user) {
        try {
            redisTemplate.opsForHash().put(KEY, Long.toString(id), user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(long id) {
        try {
            redisTemplate.opsForHash().delete(KEY,Long.toString(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
