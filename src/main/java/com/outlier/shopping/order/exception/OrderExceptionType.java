package com.outlier.shopping.order.exception;

import com.outlier.shopping.global.exception.AbstractExceptionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderExceptionType implements AbstractExceptionType {

    EMPTY_ORDER_LIST(HttpStatus.BAD_REQUEST, "empty order list")

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
