package com.msg.after_school.domain.auth.service.impl;

import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.auth.service.RefreshService;
import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.domain.user.repository.UserRepository;
import com.msg.after_school.global.security.JwtTokenProvider;
import com.msg.after_school.global.security.exception.InvalidTokenException;
import com.msg.after_school.global.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshServiceImpl implements RefreshService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenDto execute(String refresh) {
        String email = jwtTokenProvider.exactEmailFromToken(refresh);
        User user = userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(refresh, user.getRefreshToken())) {
            throw InvalidTokenException.EXCEPTION;
        }
        String accessToken = jwtTokenProvider.generateAccessToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);
        user.updateRefreshToken(passwordEncoder.encode(refreshToken));

        return TokenDto.builder()
                .accessToken(accessToken)
                .accessExp(60 * 15)
                .refreshToken(refreshToken)
                .refreshExp(60 * 60 * 24 * 7)
                .build();
    }
}
