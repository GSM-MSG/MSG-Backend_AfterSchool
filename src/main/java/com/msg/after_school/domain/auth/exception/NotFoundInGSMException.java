package com.msg.after_school.domain.auth.exception;

import com.msg.after_school.global.error.exception.ErrorCode;
import com.msg.after_school.global.error.exception.GlobalException;

public class NotFoundInGSMException extends GlobalException {
    public NotFoundInGSMException() {
        super(ErrorCode.NOT_FOIND_IN_GSM);
    }
}
