package com.summerHack.diningTogether.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Instant;

@Data
@RedisHash("UserCode")
public class UserCodeDTO implements Serializable {

    private long userId; //the id of users
    private String verificationCode;
    //private Instant tokenExpires;
    /*public boolean isTokenExpired(){
        return Instant.now().isAfter(tokenExpires);
    }*/

}
