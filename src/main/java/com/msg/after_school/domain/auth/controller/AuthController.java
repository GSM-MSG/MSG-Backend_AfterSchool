package com.msg.after_school.domain.auth.controller;

import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.auth.service.LoginService;
import com.msg.after_school.domain.auth.service.RedirectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;
    private final RedirectService redirectService;

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity redirectGoogleInitUrl() {
        return loginService.execute();
    }

    @GetMapping("/redirect")
    public String redirectUrl(@RequestParam(value = "code") String code, HttpServletResponse response) {
        TokenDto token = redirectService.execute(code);
        Cookie accessCookie = new Cookie("accessToken", token.getAccessToken());
        accessCookie.setPath("/");
        accessCookie.setMaxAge(token.getAccessExp());

        Cookie refreshCookie = new Cookie("refreshToken", token.getRefreshToken());
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(token.getRefreshExp());

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return "FRONTURL";
    }
}
