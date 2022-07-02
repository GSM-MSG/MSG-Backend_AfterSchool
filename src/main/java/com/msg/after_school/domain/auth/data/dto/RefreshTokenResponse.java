package com.msg.after_school.domain.auth.data.dto;

import lombok.AllArgsConstructor;

import javax.servlet.http.Cookie;

@AllArgsConstructor
public class RefreshTokenResponse {
    Cookie accessToken;
    Cookie refreshToken;
}
