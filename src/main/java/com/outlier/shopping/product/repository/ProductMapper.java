package com.outlier.shopping.product.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    @Insert("insert into product(name, price, manager_id) " +
            "value(#{name}, #{price}, #{manager_id})")
    void save(
            @Param("name") String name,
            @Param("price") int price,
            @Param("manager_id") Long managerId
    );
}
