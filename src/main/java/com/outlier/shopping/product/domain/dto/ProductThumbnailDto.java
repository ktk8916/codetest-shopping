package com.outlier.shopping.product.domain.dto;

import com.outlier.shopping.product.domain.entity.Product;
import lombok.Builder;

@Builder
public record ProductThumbnailDto(
        Long id,
        String name,
        int price
) {
    public static ProductThumbnailDto fromEntity(Product product){
        return ProductThumbnailDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
