package com.msg.after_school.global.security.exception;

import com.msg.after_school.global.error.exception.AfterSchoolException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class ExpiredTokenException extends AfterSchoolException {
    public static final AfterSchoolException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

}