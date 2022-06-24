package com.msg.after_school.global.security.exception;

import com.msg.after_school.global.error.exception.GlobalException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class ExpiredTokenException extends GlobalException {
    public static final GlobalException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

}