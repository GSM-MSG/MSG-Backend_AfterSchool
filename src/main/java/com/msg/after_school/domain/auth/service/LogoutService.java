package com.msg.after_school.domain.auth.service;

import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final UserFacade userFacade;

    public void execute() {
        User user = userFacade.getCurrentUser();
        user.updateRefreshToken(null);
    }
}
