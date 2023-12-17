package com.outlier.shopping.order.domain.dto;

import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OrderDetailDto {
        private int quantity;
        private int orderPrice;
        private ProductThumbnailDto product;
}
