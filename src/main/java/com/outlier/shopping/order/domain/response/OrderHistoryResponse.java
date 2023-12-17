package com.outlier.shopping.order.domain.response;

import com.outlier.shopping.order.domain.dto.OrderReceiptDto;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderHistoryResponse(
        List<OrderReceiptDto> orders,
        int page,
        int size,
        int totalSize
) {
    public static OrderHistoryResponse of(List<OrderReceiptDto> orders, int page, int size, int totalSize){
        return OrderHistoryResponse.builder()
                .orders(orders)
                .page(page)
                .size(size)
                .totalSize(totalSize)
                .build();
    }
}
