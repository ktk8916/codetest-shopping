package com.outlier.shopping.product.domain.request;

public record CreateProductRequest(
    String name,
    int price
) {
}
