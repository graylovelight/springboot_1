package com.example.springboot_1.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT_tool {
    private static final String SIGNING_ALGORITHM = SignatureAlgorithm.HS256.getValue();

    @Value("${jwt.expire}")
    private int jwtExpire;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String Jwtbuild(String subject) {
        long currentTimeMillis = System.currentTimeMillis();
        Date expirationDate = new Date(currentTimeMillis + jwtExpire * 1000L);

        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.valueOf(SIGNING_ALGORITHM), jwtSecret)
                .setExpiration(expirationDate)
                .compact();
    }

    public Claims Jwtanalysis(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
}
