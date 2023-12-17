package com.outlier.shopping.order.repository;

import com.outlier.shopping.order.domain.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {

    void save(OrderItem orderItem);
}
