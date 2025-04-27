package com.example.demo.serivces.ServiceImplement;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

@Service
public class JwtService {

    private static final String SECRET_KEY = "YourSecretKeyMustBeAtLeast256BitsLongForHS256Algorithm"; // Ensure this is 256 bits long
    private static final Logger logger = Logger.getLogger(JwtService.class.getName());

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        try {
            // Parse the token with the correct signing key
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            logger.severe("JWT token has expired");
            throw new RuntimeException("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            logger.severe("JWT token is unsupported");
            throw new RuntimeException("Unsupported JWT token", e);
        } catch (MalformedJwtException e) {
            logger.severe("JWT token is malformed");
            throw new RuntimeException("Malformed JWT token", e);
        } catch (SignatureException e) {
            logger.severe("Invalid JWT signature");
            throw new RuntimeException("Invalid JWT signature", e);
        } catch (JwtException e) {
            logger.severe("Invalid JWT token: " + e.getMessage());
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }
}
