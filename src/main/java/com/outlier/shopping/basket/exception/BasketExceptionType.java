package com.outlier.shopping.basket.exception;

import com.outlier.shopping.global.exception.AbstractExceptionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BasketExceptionType implements AbstractExceptionType {

    BASKET_NOT_FOUND(HttpStatus.NOT_FOUND, "basket not found"),
    INVALID_BASKET_OWNER(HttpStatus.UNAUTHORIZED, "invalid basket owner")

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
