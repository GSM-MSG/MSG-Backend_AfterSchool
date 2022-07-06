package com.msg.after_school.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msg.after_school.domain.auth.utils.CookieUtil;
import com.msg.after_school.global.security.exception.handler.JwtExceptionHandler;
import com.msg.after_school.global.security.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final CookieUtil cookieUtil;

    @Value("${front}")
    private String frontUrl;

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("http://localhost:8080", frontUrl)
                .allowedMethods("GET", "POST", "PUT", "PATCH" , "DELETE", "OPTIONS")
                .allowCredentials(true);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                .antMatchers(HttpMethod.GET, "/afterschool").authenticated()
                .antMatchers(HttpMethod.GET, "/afterschool/*").authenticated()
                .antMatchers(HttpMethod.POST, "/afterschool/*").authenticated()
                .antMatchers(HttpMethod.GET, "/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/redirect").permitAll()
                .antMatchers(HttpMethod.PATCH, "/auth/refresh").permitAll()
                .antMatchers(HttpMethod.PATCH, "/auth").authenticated()
                .antMatchers(HttpMethod.GET, "/auth/chk").authenticated()

                .anyRequest().denyAll()
                .and()

                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
                .and()

                .addFilterAfter(new JwtTokenFilter(jwtTokenProvider, cookieUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionHandler(objectMapper), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
