package com.outlier.shopping.cart.domain.response;

import com.outlier.shopping.cart.domain.dto.CartItemDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CartItemResponse(
        List<CartItemDto> items,
        int totalPrice
) {
    public static CartItemResponse of(List<CartItemDto> items, int totalPrice){
        return CartItemResponse.builder()
                .items(items)
                .totalPrice(totalPrice)
                .build();
    }
}
