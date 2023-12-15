package com.outlier.shopping.cart.domain.reqeust;

public record ItemAddRequest(
        Long productId,
        int quantity
) {
}
