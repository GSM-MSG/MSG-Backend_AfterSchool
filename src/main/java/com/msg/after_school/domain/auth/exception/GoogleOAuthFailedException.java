package com.msg.after_school.domain.auth.exception;

import com.msg.after_school.global.error.exception.ErrorCode;
import com.msg.after_school.global.error.exception.GlobalException;

public class GoogleOAuthFailedException extends GlobalException {
    public GoogleOAuthFailedException() {
        super(ErrorCode.FAILED_TO_GOOGLE_LOGIN);
    }
}
