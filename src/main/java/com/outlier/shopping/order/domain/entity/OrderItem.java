package com.outlier.shopping.order.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItem {

    private Long id;
    private Long orderId;
    private Long productId;
    private int orderPrice;
    private int quantity;

    public static OrderItem createOrderItem(Long orderId, Long productId, int orderPrice, int quantity){
        return OrderItem.builder()
                .orderId(orderId)
                .productId(productId)
                .orderPrice(orderPrice)
                .quantity(quantity)
                .build();
    }

    @Builder
    public OrderItem(Long orderId, Long productId, int orderPrice, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }
}
