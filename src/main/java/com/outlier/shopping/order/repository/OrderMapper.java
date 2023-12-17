package com.outlier.shopping.order.repository;

import com.outlier.shopping.order.domain.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void save(Order order);
}
