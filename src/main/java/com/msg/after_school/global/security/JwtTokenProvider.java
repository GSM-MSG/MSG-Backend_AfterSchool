package com.msg.after_school.global.security;

import com.msg.after_school.global.security.auth.AuthDetailsService;
import com.msg.after_school.global.security.exception.ExpiredTokenException;
import com.msg.after_school.global.security.exception.InvalidTokenException;
import com.msg.after_school.global.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;


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

    private Claims getTokenBody(String token) {

        try {
            return Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(jwtProperties.getSecret().getBytes()))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (SignatureException e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).get("email", String.class);
    }
}
