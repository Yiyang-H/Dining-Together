package com.summerHack.diningTogether.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserCodeDTO implements Serializable {

    private long id; //the id of users
    private String verificationCode;
}
