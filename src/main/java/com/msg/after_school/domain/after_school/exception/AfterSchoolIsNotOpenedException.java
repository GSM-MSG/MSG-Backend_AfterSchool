package com.msg.after_school.domain.after_school.exception;

import com.msg.after_school.global.error.exception.ErrorCode;
import com.msg.after_school.global.error.exception.GlobalException;

public class AfterSchoolIsNotOpenedException extends GlobalException {
    public AfterSchoolIsNotOpenedException() { super(ErrorCode.AFTERSCHOOL_IS_NOT_OPENED); }
}
