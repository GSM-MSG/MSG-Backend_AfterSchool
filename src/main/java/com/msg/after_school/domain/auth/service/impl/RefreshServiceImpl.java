package com.msg.after_school.domain.auth.service.impl;

import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.auth.service.RefreshService;
import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.security.JwtTokenProvider;
import com.msg.after_school.global.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDao userDao;

    public TokenDto execute() {
        User user = userDao.getCurrentUser();
        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());
        user.updateRefreshToken(refreshToken);

        return TokenDto.builder()
                .accessToken(accessToken)
                .accessExp(60 * 15)
                .refreshToken(refreshToken)
                .refreshExp(60 * 60 * 24 * 7)
                .build();
    }
}
