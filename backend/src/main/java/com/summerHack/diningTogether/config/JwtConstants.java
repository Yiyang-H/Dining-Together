package com.summerHack.diningTogether.config;

import org.springframework.stereotype.Component;

@Component
public class JwtConstants {

    public long getAccessTokenValiditySeconds() {
        return 5 * 60 * 60;
    }

    public String getSigningKey() {
        return "211822d8-462a-4cc5-b2f3-2534789b9062";
    }

    public String getTokenPrefix() {
        return "Bearer ";
    }

    public String getHeaderString() {
        return "Authorization";
    }
}
