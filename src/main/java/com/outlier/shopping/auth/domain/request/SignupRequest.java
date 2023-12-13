package com.outlier.shopping.auth.domain.request;

public record SignupRequest(
        String username,
        String password,
        String nickname
) {
}
