package com.msg.after_school.global.security.exception;

import com.msg.after_school.global.error.exception.GlobalException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class InvalidTokenException extends GlobalException {
    public static final GlobalException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}