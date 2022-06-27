package com.msg.after_school.domain.auth.controller;

import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.auth.service.LoginService;
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

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity redirectGoogleInitUrl() {
        return loginService.execute();
    }

    @GetMapping("/redirect")
    public String redirectUrl(@RequestParam(value = "code") String code, HttpServletResponse response) {
        TokenDto token = redirectService.execute(code);
        Cookie accessCookie = cookieUtil.createCookie("accessToken", token.getAccessToken(), token.getAccessExp());
        Cookie refreshCookie = cookieUtil.createCookie("refreshToken", token.getRefreshToken(), token.getRefreshExp());
        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return "redirect://FRONTURL/";
    }

    @PatchMapping("/refresh")
    public void refresh(HttpServletResponse res) {
        TokenDto token = refreshService.execute();
        Cookie accessCookie = cookieUtil.createCookie("accessToken", token.getAccessToken(), token.getAccessExp());
        Cookie refreshCookie = cookieUtil.createCookie("refreshToken", token.getRefreshToken(), token.getRefreshExp());
        res.addCookie(accessCookie);
        res.addCookie(refreshCookie);
    }
}
