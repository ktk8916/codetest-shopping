package com.outlier.shopping.cart.exception;

import com.outlier.shopping.global.exception.AbstractExceptionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CartExceptionType implements AbstractExceptionType {

    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "cart item not found"),
    INVALID_CART_OWNER(HttpStatus.UNAUTHORIZED, "invalid cart owner")

    ;

    private final HttpStatus httpStatus;
    private final String message;
}
