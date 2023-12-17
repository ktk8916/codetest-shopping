package com.outlier.shopping.product.domain.dto;

import com.outlier.shopping.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ProductThumbnailDto {

    private Long id;
    private String name;
    private int price;

    public static ProductThumbnailDto fromEntity(Product product){
        return ProductThumbnailDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
