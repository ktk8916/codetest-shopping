package com.outlier.shopping.cart.domain.dto;

public record CartItemDto(
    Long id,
    String name,
    int price,
    int quantity
) {
}
