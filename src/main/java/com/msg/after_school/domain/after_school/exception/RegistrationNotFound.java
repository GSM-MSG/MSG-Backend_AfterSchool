package com.msg.after_school.domain.after_school.exception;

import com.msg.after_school.global.error.exception.ErrorCode;
import com.msg.after_school.global.error.exception.GlobalException;

public class RegistrationNotFound extends GlobalException {
    public RegistrationNotFound() {
        super(ErrorCode.USER_NOT_FOUND_REGISTRATION);
    }
}
