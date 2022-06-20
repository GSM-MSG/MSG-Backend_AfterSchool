package com.msg.after_school.domain.user.exception;

import com.msg.after_school.global.error.exception.AfterSchoolException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class UserNotFoundException extends AfterSchoolException {
    public static final AfterSchoolException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
