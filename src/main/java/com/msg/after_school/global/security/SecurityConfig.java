package com.msg.after_school.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msg.after_school.domain.auth.utils.CookieUtil;
import com.msg.after_school.global.error.CustomAuthenticationEntryPoint;
import com.msg.after_school.global.security.exception.handler.JwtExceptionHandler;
import com.msg.after_school.global.security.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final CookieUtil cookieUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                .antMatchers(HttpMethod.GET, "/afterschool/*").authenticated()
                .antMatchers(HttpMethod.POST, "/afterschool/*").authenticated()
                .antMatchers(HttpMethod.GET, "/auth/*").permitAll()
                .antMatchers(HttpMethod.PATCH, "/auth/refresh").authenticated()
                .antMatchers(HttpMethod.PATCH, "/auth").authenticated()

                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))

                .and()
                .addFilterAfter(new JwtTokenFilter(jwtTokenProvider, cookieUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionHandler(objectMapper), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
