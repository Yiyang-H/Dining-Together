package com.summerHack.diningTogether.utils;

import com.summerHack.diningTogether.config.ApplicationProperties;
import com.summerHack.diningTogether.model.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.function.Function;

// from https://github.com/only2dhir/spring-boot-jwt/blob/master/src/main/java/com/devglan/config/JwtTokenUtil.java
@Component
@AllArgsConstructor
public class JwtTokenUtil implements Serializable {

    private final ApplicationProperties properties;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails user) {
        return doGenerateToken(user.getUsername(), user.getId());
    }

    private String doGenerateToken(String subject, Long userId) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Collections.emptyList()); // don't need scoops
        claims.put("userId", userId);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer("https://diningtogether.com")
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + properties.getAccessTokenValiditySeconds() * 1000))
            .signWith(getSigningKey())
            .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
            username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(properties.getJwtSigningKey().getBytes(StandardCharsets.UTF_8));
    }
}