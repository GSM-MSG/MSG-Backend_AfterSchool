package com.msg.after_school.domain.auth.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.Cookie;

@Getter
@AllArgsConstructor
public class RefreshTokenResponse {
    Cookie accessToken;
    Cookie refreshToken;
}
