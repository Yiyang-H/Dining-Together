package com.summerHack.diningTogether.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {
    private String allowedOrigins;

    private Integer defaultCurrency;

    private Integer accessTokenValiditySeconds;

    private String jwtSigningKey;
}