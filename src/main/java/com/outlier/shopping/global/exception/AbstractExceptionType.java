package com.outlier.shopping.global.exception;

import org.springframework.http.HttpStatus;

public interface AbstractExceptionType {

    HttpStatus getHttpStatus();
    String getMessage();
}
