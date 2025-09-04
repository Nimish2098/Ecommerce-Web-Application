package com.Ecommerce.Backend.security;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.PublicKey;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtil {
    private final String SECRET = "supersecretkeysupersecretkeysupersecretkey";
    private final long EXPIRATION = 1000*60*60;

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email, Set<String> roles){
        return Jwts.builder()
                .setSubject(email)
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser().verifyWith((PublicKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith((PublicKey) key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
            return true;
        }
        catch (JwtException e){
            return false;
        }
    }
}
