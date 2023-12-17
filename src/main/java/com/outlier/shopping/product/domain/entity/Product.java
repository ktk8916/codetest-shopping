package com.outlier.shopping.product.domain.entity;

import com.outlier.shopping.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {

    private Long id;
    private String name;
    private int price;
    private Member manager; // 상품을 등록한 사람

    public static Product createProduct(String name, int price, Member manager){
        return Product.builder()
                .name(name)
                .price(price)
                .manager(manager)
                .build();
    }

    @Builder
    public Product(String name, int price, Member manager) {
        this.name = name;
        this.price = price;
        this.manager = manager;
    }
}
