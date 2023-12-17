package com.outlier.shopping.cart.domain.entity;

import com.outlier.shopping.member.domain.entity.Member;
import com.outlier.shopping.product.domain.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItem {

    private Long id;
    private Member member;
    private Product product;
    private int quantity;

    public static CartItem createCartItem(Member member, Product product, int quantity){
        return CartItem.builder()
                .member(member)
                .product(product)
                .quantity(quantity)
                .build();
    }

    @Builder
    public CartItem(Member member, Product product, int quantity) {
        this.member = member;
        this.product = product;
        this.quantity = quantity;
    }
}
