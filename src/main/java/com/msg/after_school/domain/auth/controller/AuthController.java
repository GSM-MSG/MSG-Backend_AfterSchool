package com.msg.after_school.domain.auth.controller;

import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.auth.service.LoginService;
import com.msg.after_school.domain.auth.service.LogoutService;
import com.msg.after_school.domain.auth.service.RedirectService;
import com.msg.after_school.domain.auth.service.RefreshService;
import com.msg.after_school.domain.auth.utils.CookieUtil;
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
    public String redirectUrl(@RequestParam(value = "code") String code, HttpServletResponse response) {
        TokenDto token = redirectService.execute(code);

        if (token == null) {
            return "redirect://"+ frontUrl +"/login";
        }
        Cookie accessCookie = cookieUtil.createCookie("accessToken", token.getAccessToken(), token.getAccessExp());
        Cookie refreshCookie = cookieUtil.createCookie("refreshToken", token.getRefreshToken(), token.getRefreshExp());
        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return "redirect://" + frontUrl;
    }

    @PatchMapping("/refresh")
    public void refresh(HttpServletResponse res) {
        TokenDto token = refreshService.execute();
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