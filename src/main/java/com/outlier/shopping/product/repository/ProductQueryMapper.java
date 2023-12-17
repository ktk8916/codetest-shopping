package com.outlier.shopping.product.repository;

import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductQueryMapper {
    List<ProductThumbnailDto> findProductThumbnailByCondition(String keyword, int limit, int offset);

    int findTotalSizeByCondition(String keyword);
}
