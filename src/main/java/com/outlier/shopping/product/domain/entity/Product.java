package com.outlier.shopping.product.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {

    private Long id;
    private String name;
    private int price;
    private Long managerId; // 상품을 등록한 사람

    @Builder
    public Product(String name, int price, Long managerId) {
        this.name = name;
        this.price = price;
        this.managerId = managerId;
    }
}
