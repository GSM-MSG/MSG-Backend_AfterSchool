package com.msg.after_school.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    DAY_OF_WEEK_NOT_FOUND(404, "Not Found Day Of Week"),
    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    USER_NOT_FOUND(404, "Not Found User"),
    AFTERSHCOOL_CONFLICT(409,"Conflict AfterSchool"),
    UNKNOWN(500, "Unknown error"),
    GRADE_NOT_FOUND(404,"Not Found Grade"),
    AFTERSCHOOL_NOT_FOUND(404,"Not Found AfterSchool");

    private final int status;
    private final String message;
}
