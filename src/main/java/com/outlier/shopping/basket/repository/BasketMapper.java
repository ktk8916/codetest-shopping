package com.outlier.shopping.basket.repository;

import com.outlier.shopping.basket.domain.entity.Basket;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface BasketMapper {

    @Insert("insert into basket(member_id, product_id, quantity) " +
            "value(#{memberId}, #{productId}, #{quantity})")
    void save(
            @Param("memberId") Long memberId,
            @Param("productId") Long productId,
            @Param("quantity") int quantity
    );

    @Select("select * from basket b " +
            "where b.member_id = #{memberId} " +
            "and b.product_id = #{productId}")
    Optional<Basket> findByMemberIdAndProductId(
            @Param("memberId") Long memberId,
            @Param("productId") Long productId
    );

    @Update("update basket set quantity = #{quantity} " +
            "where id = #{basketId}")
    void updateQuantity(
            @Param("quantity") int quantity,
            @Param("basketId") Long basketId
    );
}
