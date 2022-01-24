package com.summerHack.diningTogether.dto;

import lombok.Data;

@Data
public class AuthorizeOutput {

    String username;

    Long userId;

    String token;

    long expiresIn;
}
