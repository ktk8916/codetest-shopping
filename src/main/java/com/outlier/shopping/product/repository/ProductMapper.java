package com.outlier.shopping.product.repository;

import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import com.outlier.shopping.product.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    void save(Product product);

    List<ProductThumbnailDto> findProductThumbnailByCondition(
        @Param("keyword") String keyword,
        @Param("limit") int limit,
        @Param("offset") int offset
    );

    @Select("select count(*) " +
            "from product p " +
            "where p.name like #{keyword} ")
    int findTotalSizeByCondition(@Param("keyword") String keyword);

}
