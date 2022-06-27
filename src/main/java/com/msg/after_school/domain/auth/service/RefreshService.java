package com.msg.after_school.domain.auth.service;

import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.security.JwtTokenProvider;
import com.msg.after_school.global.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    public TokenDto execute() {
        User user = userFacade.getCurrentUser();
        String accesToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        user.updateRefreshToken(refreshToken);

        return TokenDto.builder()
                .accessToken(accesToken)
                .accessExp(60 * 15)
                .refreshToken(refreshToken)
                .refreshExp(60 * 60 * 24 * 7)
                .build();
    }
}
