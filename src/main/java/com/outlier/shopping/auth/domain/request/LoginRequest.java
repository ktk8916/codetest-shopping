package com.outlier.shopping.auth.domain.request;

public record LoginRequest(
        String username,
        String password
) {
}
