package com.outlier.shopping.product.domain.response;

import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductSearchResponse(
    List<ProductThumbnailDto> products,
    int page,
    int size,
    int totalSize
) {
    public static ProductSearchResponse of(
            List<ProductThumbnailDto> products,
            int page,
            int size,
            int totalSize
    ){
        return ProductSearchResponse.builder()
                .products(products)
                .page(page)
                .size(size)
                .totalSize(totalSize)
                .build();
    }
}
