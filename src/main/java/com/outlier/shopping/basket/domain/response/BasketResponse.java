package com.outlier.shopping.basket.domain.response;

import com.outlier.shopping.basket.domain.dto.BasketProductDto;
import lombok.Builder;

import java.util.List;

@Builder
public record BasketResponse(
        List<BasketProductDto> products,
        int totalPrice
) {
    public static BasketResponse of(List<BasketProductDto> products, int totalPrice){
        return BasketResponse.builder()
                .products(products)
                .totalPrice(totalPrice)
                .build();
    }
}
