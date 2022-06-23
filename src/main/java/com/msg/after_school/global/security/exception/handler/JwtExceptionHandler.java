package com.msg.after_school.global.security.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msg.after_school.global.error.ErrorResponse;
import com.msg.after_school.global.error.exception.GlobalException;
import com.msg.after_school.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionHandler extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        }catch(GlobalException e) {
            log.error(request.getRequestURI());
            log.error(e.getErrorCode().getMessage());
            e.printStackTrace();
            responseError(response, e.getErrorCode());
        }
    }

    private void responseError(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
        String valueAsString = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(valueAsString);
    }
}
