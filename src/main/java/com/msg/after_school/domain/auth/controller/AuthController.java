package com.msg.after_school.domain.auth.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.msg.after_school.domain.auth.data.dto.GoogleLoginDto;
import com.msg.after_school.domain.auth.data.dto.GoogleLoginRequest;
import com.msg.after_school.domain.auth.data.dto.GoogleLoginResponse;
import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.auth.exception.GoogleOAuthFailedException;
import com.msg.after_school.domain.auth.service.LoginService;
import com.msg.after_school.domain.auth.service.RedirectService;
import com.msg.after_school.global.security.utils.ConfigUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final LoginService loginService;
    private final RedirectService redirectService;

    @GetMapping("/login")
    public ResponseEntity redirectGoogleInitUrl() {
        return loginService.execute();
    }

    @GetMapping("/redirect")
    public void redirectUrl(@RequestParam(value = "code") String code, HttpServletResponse response) {
        TokenDto token = redirectService.execute(code);
        Cookie accessCookie = new Cookie("accessToken", "Bearer " + token.getAccessToken());
        accessCookie.setPath("/");
        accessCookie.setMaxAge(Integer.parseInt(String.valueOf(token.getAccessExp())));

        Cookie refreshCookie = new Cookie("refreshToken", "Bearer " + token.getRefreshToken());
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(Integer.parseInt(String.valueOf(token.getRefreshExp())));
    }
}
