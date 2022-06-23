package com.msg.after_school.domain.after_school.exception;

import com.msg.after_school.global.error.exception.ErrorCode;
import com.msg.after_school.global.error.exception.GlobalException;

public class AfterSchoolNotFoundException extends GlobalException {

    public AfterSchoolNotFoundException(){
        super(ErrorCode.AFTERSCHOOL_NOT_FOUND);
    }
}
