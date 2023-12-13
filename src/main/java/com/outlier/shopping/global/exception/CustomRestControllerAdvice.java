package com.outlier.shopping.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e){
        return ResponseEntity.status(e.getHttpStatus())
                .body(ExceptionResponse.of(e.getHttpStatus(), e.getMessage()));
    }
}
