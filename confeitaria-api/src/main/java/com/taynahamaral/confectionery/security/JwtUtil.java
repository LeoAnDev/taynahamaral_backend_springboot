package com.taynahamaral.confectionery.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // ou configure via application.properties
    private final long expirationMs = 1000 * 60 * 60 * 24; // 24h

    public String generateToken(String email, Set<String> roles) {
        String rolesStr = String.join(",", roles);

        return Jwts.builder()
                .setSubject(email)
                .claim("roles", rolesStr)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    public Set<String> getRolesFromToken(String token) {
        String rolesStr = (String) parseClaims(token).getBody().get("roles");
        return Set.of(rolesStr.split(","));
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
