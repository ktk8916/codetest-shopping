package com.outlier.shopping.order.domain.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BillResponse(
        Long orderId,
        int totalPrice,
        LocalDateTime createdAt
) {
    public static BillResponse of(Long orderId, int totalPrice, LocalDateTime createdAt){
        return BillResponse.builder()
                .orderId(orderId)
                .totalPrice(totalPrice)
                .createdAt(createdAt)
                .build();
    }
}
