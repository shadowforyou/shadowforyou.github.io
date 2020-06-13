package com.poi.testpoi;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.poi.testpoi.common.ApiResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> apiExceptionHandler(HttpServletRequest request, Exception e){
        ApiResponse<Object> response = new ApiResponse<>();
        response.setMessage(e.getMessage());
        response.setSuccess(false);
        return response;
    }
}
