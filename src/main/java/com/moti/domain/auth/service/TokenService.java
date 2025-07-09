package com.moti.domain.auth.service;

import com.moti.domain.auth.domain.Token;
import com.moti.domain.auth.domain.TokenRepository;
import com.moti.domain.auth.domain.TokenType;
import com.moti.domain.auth.domain.exception.ExpiredTokenException;
import com.moti.domain.auth.domain.exception.InvalidTokenException;
import com.moti.domain.user.domain.User;
import com.moti.shared.config.properties.JwtProperties;
import com.moti.domain.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtProperties jwtProperties;
    private final TokenRepository tokenRepository;
    private final UserService userService;

    public String generateAccessToken(String id) {
        return generateToken(id, TokenType.ACCESS_TOKEN, jwtProperties.getAccessExpirationTime());
    }

    public String generateRefreshToken(String id) {
        String token = generateToken(id, TokenType.REFRESH_TOKEN, jwtProperties.getRefreshExpirationTime());
        tokenRepository.save(
                Token.builder()
                        .id(id)
                        .token(token)
                        .build()
        );

        return token;
    }

    public String generateToken(String id, TokenType type, Long expirationTime) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("type", type.name());

        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(getSigningKey(jwtProperties.getSecretKey()), SignatureAlgorithm.HS256)
                .compact();
    }

    public User getUser(String token) {
        return userService.getUser(getId(token));
    }

    public String getId(String token) {
        return extractAllClaims(token).get("id", String.class);
    }

    public String getType(String token) {
        return extractAllClaims(token).get("type", String.class);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(jwtProperties.getSecretKey()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
