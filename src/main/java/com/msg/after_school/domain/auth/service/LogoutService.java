package com.msg.after_school.domain.auth.service;

import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final UserDao userDao;

    public void execute() {
        User user = userDao.getCurrentUser();
        user.updateRefreshToken(null);
    }
}
