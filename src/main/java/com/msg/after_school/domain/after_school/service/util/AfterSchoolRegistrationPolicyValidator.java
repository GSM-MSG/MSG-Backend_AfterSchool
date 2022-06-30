package com.msg.after_school.domain.after_school.service.util;

import com.msg.after_school.domain.after_school.data.entity.AfterSchool;
import com.msg.after_school.domain.user.data.entity.User;

public interface AfterSchoolRegistrationPolicyValidator {
    void validate(AfterSchool afterSchoolInfo, User userInfo);
    void validateCancelPolicy(AfterSchool afterSchoolInfo, User userInfo);
}
