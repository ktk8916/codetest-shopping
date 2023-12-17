package com.outlier.shopping.order.repository;

import com.outlier.shopping.order.domain.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface OrderMapper {

    @Insert("insert into orders(member_id, total_price) " +
            "value(#{memberId}, #{totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Order order);
}
