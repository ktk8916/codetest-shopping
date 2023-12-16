package com.outlier.shopping.cart.domain.dto;

import lombok.Builder;

@Builder
public record CartItemDto(
    Long id,
    String name,
    int price,
    int quantity
) {
}
