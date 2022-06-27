package com.msg.after_school.domain.auth.utils;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieUtil {
    public Cookie createCookie(String name, String value, Integer exp) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(exp);
        cookie.setPath("/");
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(name))
                return cookie;
        }
        return null;
    }
}
