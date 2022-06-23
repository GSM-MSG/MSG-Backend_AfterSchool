package com.msg.after_school.error;

import com.msg.after_school.global.error.exception.AfterSchoolException;
import com.msg.after_school.global.error.exception.ErrorCode;

public class DayOfWeekNotFoundException extends AfterSchoolException {
    public static final AfterSchoolException EXCEPTION = new DayOfWeekNotFoundException();

    private DayOfWeekNotFoundException() {
        super(ErrorCode.DAY_OF_WEEK_NOT_FOUND);
    }
}