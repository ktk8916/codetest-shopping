package com.outlier.shopping.cart.domain.entity;

import com.outlier.shopping.product.domain.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartItemTest {

    @DisplayName("상품의 총 가격을 계산한다.")
    @Test
    void getSumPrice(){
        // given
        Product product = Product.builder()
                .price(1000)
                .build();

        CartItem cartItem = CartItem.builder()
                .product(product)
                .quantity(5)
                .build();

        // when
        int sumPrice = cartItem.getSumPrice();

        // then
        assertThat(sumPrice).isEqualTo(5000);
    }
}