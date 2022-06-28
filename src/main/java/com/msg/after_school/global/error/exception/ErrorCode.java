package com.msg.after_school.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    BAD_REQUEST_ALREADY_EXIST (400,"Already exist"),
    BAD_REQUEST_ALREADY_JOINED_ANOTHER_AFTERSCHOOL (400,"Already joined another afterSchool"),
    USER_NOT_FOUND_REGISTRATION(400,"Not Found Register User"),

    EXPIRED_TOKEN(401, "Expired Token"),
    INVALID_TOKEN(401, "Invalid Token"),
    UNAUTHORIZED(401, "Unauthorized"),

    FAILED_TO_GOOGLE_LOGIN(403, "Failed to login with Google OAuth"),

    USER_NOT_FOUND(404, "Not Found User"),
    NOT_FOIND_IN_GSM(404, "This user is not exist in gsm"),
    DAY_OF_WEEK_NOT_FOUND(404, "Not Found Day Of Week"),
    GRADE_NOT_FOUND(404,"Not Found Grade"),
    AFTERSCHOOL_NOT_FOUND(404,"Not Found AfterSchool"),

    UNKNOWN(500, "Unknown error");


    private final int status;
    private final String message;
}
