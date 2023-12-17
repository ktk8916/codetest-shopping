package com.outlier.shopping.cart.domain.reqeust;

import jakarta.validation.constraints.Positive;

public record ItemAddRequest(

        @Positive(message = "product id must be positive")
        Long productId,
        @Positive(message = "quantity must be positive")
        int quantity
) {
}
