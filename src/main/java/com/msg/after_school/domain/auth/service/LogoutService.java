package com.msg.after_school.domain.auth.service;

import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.user.dao.impl.UserDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final UserDaoImpl userDao;

    public void execute() {
        User user = userDao.getCurrentUser();
        user.updateRefreshToken(null);
    }
}
