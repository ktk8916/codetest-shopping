package com.outlier.shopping.product.repository;

import com.outlier.shopping.product.domain.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    void save(Product product);
}
