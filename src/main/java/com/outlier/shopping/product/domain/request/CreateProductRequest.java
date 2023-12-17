package com.outlier.shopping.product.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateProductRequest(
        @NotBlank(message = "name is required")
        String name,
        @Positive(message = "price must be positive")
        int price
) {
}
