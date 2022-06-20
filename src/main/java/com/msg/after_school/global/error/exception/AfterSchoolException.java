package com.msg.after_school.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AfterSchoolException extends RuntimeException{
    private final ErrorCode errorCode;
}
