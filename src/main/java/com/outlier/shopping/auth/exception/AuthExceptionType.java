package com.outlier.shopping.auth.exception;

import com.outlier.shopping.global.exception.AbstractExceptionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthExceptionType implements AbstractExceptionType {

    INVALID_USERNAME(HttpStatus.UNAUTHORIZED, "invalid username"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "invalid password"),

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
