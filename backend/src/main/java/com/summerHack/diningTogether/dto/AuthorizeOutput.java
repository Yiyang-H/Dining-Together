package com.summerHack.diningTogether.dto;

import lombok.Data;

@Data
public class AuthorizeOutput {

    String username;

    String token;

    long expiresIn;
}
