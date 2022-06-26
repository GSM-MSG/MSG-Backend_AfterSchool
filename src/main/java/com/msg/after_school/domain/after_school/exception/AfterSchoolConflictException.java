package com.msg.after_school.domain.after_school.exception;

import com.msg.after_school.global.error.exception.ErrorCode;
import com.msg.after_school.global.error.exception.GlobalException;

public class AfterSchoolConflictException extends GlobalException {
    public AfterSchoolConflictException() {
        super(ErrorCode.AFTERSHCOOL_CONFLICT);
    }
}
