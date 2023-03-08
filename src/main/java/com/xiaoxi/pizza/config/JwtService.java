package com.xiaoxi.pizza.config;

import com.xiaoxi.pizza.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
  private final String SECRET_KEY = "FAKE_SECRET_KEY_gfdksjgbdgsdfgbdfmsgnbsdf5648mgn";
  private Key SIGNING_KEY = null;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> getClaim) {
    final Claims claims = extractAllClaims(token);
    return getClaim.apply(claims);
  }

  public String generateToken(User user) {
    return generateToken(new HashMap<>(), user);
  }

  public String generateToken(Map<String, Object> extraClaims, User user) {
    long now = System.currentTimeMillis();
    long expiration = 1000 * 60 * 24;

    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(now))
        .setExpiration(new Date(now + expiration))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, User user) {
    return
        extractUsername(token).equals(user.getUsername()) &&
        !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSigningKey() {
    if (SIGNING_KEY == null) {
      SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    return SIGNING_KEY;
  }
}
