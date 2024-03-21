package org.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long expirationTime;
    public String generateToken(Authentication authentication){
        return generateToken(new HashMap<>(), authentication);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            Authentication authentication){

        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token){
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
