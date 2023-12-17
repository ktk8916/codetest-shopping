package com.outlier.shopping.order.domain.entity;

import com.outlier.shopping.member.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private Long id;
    private Member member;
    private int totalPrice;
    private LocalDateTime createdAt;

    public static Order createOrder(Member member, int totalPrice){
        return Order.builder()
                .member(member)
                .totalPrice(totalPrice)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Builder
    public Order(Member member, int totalPrice, LocalDateTime createdAt) {
        this.member = member;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
}
