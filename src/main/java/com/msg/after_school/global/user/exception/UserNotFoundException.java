package com.msg.after_school.global.user.exception;

import com.msg.after_school.global.error.exception.GlobalException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
