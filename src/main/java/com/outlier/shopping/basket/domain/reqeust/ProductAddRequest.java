package com.outlier.shopping.basket.domain.reqeust;

public record ProductAddRequest(
        Long productId,
        int quantity
) {
}
