package com.outlier.shopping.order.repository;

import com.outlier.shopping.order.domain.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface OrderItemMapper {

    @Insert("insert into order_item(order_id, product_id, order_price, quantity) " +
            "value(#{orderId}, #{productId}, #{orderPrice}, #{quantity})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(OrderItem orderItem);
}
