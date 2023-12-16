package com.outlier.shopping.cart.domain.response;

import com.outlier.shopping.cart.domain.dto.CartItemDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CartItemResponse(
        List<CartItemDto> items,
        int totalPrice
) {
    public static CartItemResponse of(List<CartItemDto> cartItems, int totalPrice){
        return CartItemResponse.builder()
                .items(cartItems)
                .totalPrice(totalPrice)
                .build();
    }
}
