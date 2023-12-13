package com.outlier.shopping.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public CustomException(AbstractExceptionType type){
        this.httpStatus = type.getHttpStatus();
        this.message = type.getMessage();
    }

    public CustomException(AbstractExceptionType type, Throwable cause){
        super(cause);
        this.httpStatus = type.getHttpStatus();
        this.message = type.getMessage();
    }
}
