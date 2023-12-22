package com.example.apigateway.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public void validateToken(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {
            String refreshedToken = refreshToken(token, 30000);

            if (refreshedToken != null) {
                System.out.println("Token expired. Refreshed token: " + refreshedToken);
            } else {
                System.out.println("Token refresh failed.");
            }
        }
    }



    public String refreshToken(final String token, final long expirationTimeInMilliseconds) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();
            String username = claims.getSubject();

            return generateToken(username, expirationTimeInMilliseconds);
        } catch (Exception e) {
            return null;
        }
    }

    private String generateToken(String username, long expirationTimeInMilliseconds) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTimeInMilliseconds);

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(getSignKey())
                .compact();

        return token;
    }





    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
