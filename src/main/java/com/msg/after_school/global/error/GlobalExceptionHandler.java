package com.msg.after_school.global.error;

import com.msg.after_school.global.error.exception.AfterSchoolException;
import com.msg.after_school.global.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AfterSchoolException.class})
    public ResponseEntity<ErrorResponse> handleGlobalException(AfterSchoolException e) {
        ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity(
                new ErrorResponse(errorCode.getStatus(), errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity bindException(BindException e) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity constraintViolationException(ConstraintViolationException e) {
        Map<String, String> errorMap = new HashMap<>();
        int i = 0;
        for (ConstraintViolation error : e.getConstraintViolations()) {
            errorMap.put("error" + i++, error.getMessageTemplate());
        }
        return new ResponseEntity(errorMap, HttpStatus.BAD_REQUEST);
    }
}
