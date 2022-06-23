package com.msg.after_school.error;

import com.msg.after_school.global.error.exception.AfterSchoolException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class GradeNotFoundException extends AfterSchoolException {
    public static final AfterSchoolException EXCEPTION = new GradeNotFoundException();

    private GradeNotFoundException() {
        super(ErrorCode.GRADE_NOT_FOUND);
    }
}