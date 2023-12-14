package com.outlier.shopping.global.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ExceptionResponse(
  HttpStatus httpStatus,
  String message
) {
    public static ExceptionResponse of(HttpStatus httpStatus, String message){
        return ExceptionResponse.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }
}
