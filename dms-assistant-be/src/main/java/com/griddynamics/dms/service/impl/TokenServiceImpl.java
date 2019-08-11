package com.griddynamics.dms.service.impl;

import com.griddynamics.dms.service.TokenService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl implements TokenService {

    @Value("${signInKey}")
    private String signInKey;

    @Override
    public String encodeToken(String email) {
        return Jwts.builder()
                .claim("email", email)
                .signWith(SignatureAlgorithm.HS256, signInKey)
                .compact();
    }

    @Override
    public String decodeToken(String jwt) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(signInKey)
                .parseClaimsJws(jwt);

        return jws.getBody().get("email", String.class);
    }
}