package com.outlier.shopping.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OrderReceiptDto{
    private Long id;
    private int totalPrice;
    private LocalDateTime createdAt;
    private List<OrderDetailDto> orderItems;
}
