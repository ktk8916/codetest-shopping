package com.outlier.shopping.order.domain.entity;

import com.outlier.shopping.product.domain.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItem {

    private Long id;
    private Order order;
    private Product product;
    private int orderPrice;
    private int quantity;

    public static OrderItem createOrderItem(Order order, Product product, int orderPrice, int quantity){
        return OrderItem.builder()
                .order(order)
                .product(product)
                .orderPrice(orderPrice)
                .quantity(quantity)
                .build();
    }

    @Builder
    public OrderItem(Order order, Product product, int orderPrice, int quantity) {
        this.order = order;
        this.product = product;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }
}
