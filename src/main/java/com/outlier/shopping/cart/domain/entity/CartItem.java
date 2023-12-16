package com.outlier.shopping.cart.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartItem {

    private Long id;
    private Long memberId;
    private Long productId;
    private int quantity;

    @Builder
    public CartItem(Long memberId, Long productId, int quantity) {
        this.memberId = memberId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
