package com.msg.after_school.global.user.dao;

import com.msg.after_school.domain.user.data.entity.User;

public interface UserDao {
    User getCurrentUser();
    User getUserByEmail(String email);
}
