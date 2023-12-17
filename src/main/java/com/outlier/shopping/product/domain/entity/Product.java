package com.outlier.shopping.product.domain.entity;

import com.outlier.shopping.member.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static Product fromId(Long id){
        Product product = new Product();
        product.id = id;
        return product;
    }

    @Builder
    public Product(String name, int price, Member manager) {
        this.name = name;
        this.price = price;
        this.manager = manager;
    }
}
