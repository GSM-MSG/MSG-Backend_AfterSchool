package com.msg.after_school.domain.auth.controller;

import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.auth.service.LoginService;
import com.msg.after_school.domain.auth.service.LogoutService;
import com.msg.after_school.domain.auth.service.RedirectService;
import com.msg.after_school.domain.auth.service.RefreshService;
import com.msg.after_school.domain.auth.utils.CookieUtil;
import com.msg.after_school.global.security.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final CookieUtil cookieUtil;
    private final LoginService loginService;
    private final RedirectService redirectService;
    private final RefreshService refreshService;
    private final LogoutService logoutService;

    @Value("${front}")
    private String frontUrl;

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity redirectGoogleInitUrl() {
        return loginService.execute();
    }

    @GetMapping("/redirect")
    public void redirectUrl(@RequestParam(value = "code") String code, HttpServletResponse response) throws IOException {
        TokenDto token = redirectService.execute(code);

        if (token == null) {
            response.sendRedirect(frontUrl);
            return;
        }
        Cookie accessCookie = cookieUtil.createCookie("accessToken", token.getAccessToken(), token.getAccessExp());
        Cookie refreshCookie = cookieUtil.createCookie("refreshToken", token.getRefreshToken(), token.getRefreshExp());
        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        response.sendRedirect(frontUrl);
    }

    @PatchMapping("/refresh")
    public void refresh(HttpServletRequest req, HttpServletResponse res){
        Cookie refresh = cookieUtil.getCookie(req, "refreshToken");
        if (refresh == null) {
            throw InvalidTokenException.EXCEPTION;
        }

        TokenDto token = refreshService.execute(refresh.getValue());
        Cookie accessCookie = cookieUtil.createCookie("accessToken", token.getAccessToken(), token.getAccessExp());
        Cookie refreshCookie = cookieUtil.createCookie("refreshToken", token.getRefreshToken(), token.getRefreshExp());
        res.addCookie(accessCookie);
        res.addCookie(refreshCookie);
    }

    @PatchMapping("/")
    public void logout(HttpServletResponse res) {
        res.addCookie(cookieUtil.createCookie("accessToken", null, 0));
        res.addCookie(cookieUtil.createCookie("refreshToken", null, 0));
        logoutService.execute();
    }

    @GetMapping("/chk")
    public void check() {
        return;
    }
}
