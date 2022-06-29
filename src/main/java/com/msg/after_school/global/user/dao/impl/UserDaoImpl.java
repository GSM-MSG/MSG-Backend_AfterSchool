package com.msg.after_school.global.user.dao.impl;

import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.global.user.dao.UserDao;
import com.msg.after_school.global.user.exception.UserNotFoundException;
import com.msg.after_school.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao{
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
