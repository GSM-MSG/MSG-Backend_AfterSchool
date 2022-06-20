package com.msg.after_school.global.security.exception;

import com.msg.after_school.global.error.exception.AfterSchoolException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class InvalidTokenException extends AfterSchoolException {
    public static final AfterSchoolException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}