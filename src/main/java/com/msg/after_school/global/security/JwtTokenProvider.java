package com.msg.after_school.global.security;

import com.msg.after_school.global.security.auth.AuthDetailsService;
import com.msg.after_school.global.security.exception.ExpiredTokenException;
import com.msg.after_school.global.security.exception.InvalidTokenException;
import com.msg.after_school.global.security.properties.JwtProperties;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String id) {
        return generateToken(id, "access", jwtProperties.getAccessSecret(), 60 * 15);
    }

    public String generateRefreshToken(String id) {
        return generateToken(id, "refresh", jwtProperties.getRefreshSecret(), 60 * 60 * 24 * 7);
    }

    public String exactEmailFromToken(String token) {
        return getTokenSubject(token);
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer "))
            return token.replace("Bearer ", "");
        return null;
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String generateToken(String id, String type, String secret, Integer exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(jwtProperties.getAccessSecret().getBytes()))
                .claim("email", id)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    private Claims getTokenBody(String token) {

        try {
            return Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(jwtProperties.getAccessSecret().getBytes()))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (SignatureException | MalformedJwtException e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).get("email", String.class);
    }
}
