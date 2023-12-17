package com.outlier.shopping.order.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Order {

    private Long id;
    private Long memberId;
    private int totalPrice;
    private LocalDateTime createdAt;

    public static Order createOrder(Long memberId, int totalPrice){
        return Order.builder()
                .memberId(memberId)
                .totalPrice(totalPrice)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Builder
    public Order(Long memberId, int totalPrice, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
}
