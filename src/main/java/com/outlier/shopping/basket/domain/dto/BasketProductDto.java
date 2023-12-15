package com.outlier.shopping.basket.domain.dto;

public record BasketProductDto(
    Long id,
    String name,
    int price,
    int quantity
) {
}
