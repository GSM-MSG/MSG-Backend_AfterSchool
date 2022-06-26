package com.msg.after_school.domain.after_school.util;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.user.entity.User;

public interface AfterSchoolRegistrationPolicyValidator {
    void validate(AfterSchool afterSchoolInfo, User userInfo);
}
