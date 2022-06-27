package com.msg.after_school.domain.after_school.exception;

import com.msg.after_school.global.error.exception.ErrorCode;
import com.msg.after_school.global.error.exception.GlobalException;

public class AlreadyJoinedAnotherAfterSchoolException extends GlobalException {
    public AlreadyJoinedAnotherAfterSchoolException() {
        super (ErrorCode.BAD_REQUEST_ALREADY_JOINED_ANOTHER_AFTERSCHOOL);
    }
}
