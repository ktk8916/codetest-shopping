package com.outlier.shopping.basket.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Basket {

    private Long id;
    private Long memberId;
    private Long productId;
    private int quantity;

    @Builder
    public Basket(Long memberId, Long productId, int quantity) {
        this.memberId = memberId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
