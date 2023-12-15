package com.outlier.shopping.basket.repository;

import com.outlier.shopping.basket.domain.dto.BasketItemDto;
import com.outlier.shopping.basket.domain.entity.Basket;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BasketMapper {

    @Select("select * from basket " +
            "where id = #{basketId}")
    Optional<Basket> findById(@Param("basketId") Long basketId);

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

    @Select("select p.id, p.name, p.price, b.quantity " +
            "from basket as b inner join product p " +
            "on b.product_id = p.id " +
            "where b.member_id = #{memberId}")
    List<BasketItemDto> findMyBasketItems(@Param("memberId") Long memberId);

    @Delete("delete from basket " +
            "where member_id = #{memberId}")
    void deleteByMemberId(@Param("memberId") Long memberId);

    @Delete("delete from basket " +
            "where id = #{basketId}")
    void deleteById(@Param("basketId") Long basketId);
}
