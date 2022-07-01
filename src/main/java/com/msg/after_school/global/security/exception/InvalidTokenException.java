package com.msg.after_school.global.security.exception;

import com.msg.after_school.global.error.exception.GlobalException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class InvalidTokenException extends GlobalException {
    public static final GlobalException EXCEPTION = new InvalidTokenException(); // TODO: 다른 Exception 방식이랑 통

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}