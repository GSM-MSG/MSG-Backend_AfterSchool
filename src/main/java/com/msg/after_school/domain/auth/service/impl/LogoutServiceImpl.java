package com.msg.after_school.domain.auth.service.impl;

import com.msg.after_school.domain.auth.service.LogoutService;
import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LogoutServiceImpl implements LogoutService {
    private final UserDao userDao;

    public void execute() {
        User user = userDao.getCurrentUser();
        user.updateRefreshToken(null);
    }
}
