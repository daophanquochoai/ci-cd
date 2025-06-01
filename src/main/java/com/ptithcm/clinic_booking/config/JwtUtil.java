package com.ptithcm.clinic_booking.config;

import com.ptithcm.clinic_booking.exception.UnauthorizedException;
import com.ptithcm.clinic_booking.model.EmailOtp;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";//cấu hình trong.env
    private final long EXPIRATION = 1000 * 60 * 60 * 2;
    private final long EXPIRATION_PASSWORD = 1000 * 60 * 3;
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities());
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }
    
    public Claims extractClaims(String token){
        try {

            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT Token expired");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("JWT Token malformed");
        } catch (SignatureException e) {
            throw new RuntimeException("JWT Signature validation failed");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("JWT Token unsupported");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT Token is empty or null");
        }
    }

    public String generateResetPasswordToken(String email){
        Map<String, Object> claims = new HashMap<>();
        claims.put("purpose", EmailOtp.OtpPurpose.ACCOUNT_VERIFY.toString());
        claims.put("email", email);
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_PASSWORD))
                .signWith(key)
                .compact();
    }

    public String validateResetPasswordToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String purpose = claims.get("purpose", String.class);

            if (!EmailOtp.OtpPurpose.ACCOUNT_VERIFY.toString().equals(purpose)) {
                throw new UnauthorizedException("Invalid token purpose");
            }

            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("Token has expired");
        } catch (JwtException e) {
            throw new UnauthorizedException("Invalid token");
        }

    }
}
