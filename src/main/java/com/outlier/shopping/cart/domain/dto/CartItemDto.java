package com.outlier.shopping.cart.domain.dto;

import com.outlier.shopping.cart.domain.entity.CartItem;
import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import lombok.Builder;

@Builder
public record CartItemDto(
    Long id,
    int quantity,
    ProductThumbnailDto product
) {
    public static CartItemDto fromEntity(CartItem cartItem){
        return CartItemDto.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .product(ProductThumbnailDto.fromEntity(cartItem.getProduct()))
                .build();
    }
}
