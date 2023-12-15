package com.outlier.shopping.cart.repository;

import com.outlier.shopping.cart.domain.dto.CartItemDto;
import com.outlier.shopping.cart.domain.entity.CartItem;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

    @Select("select * from cart_item " +
            "where id = #{cartItemId}")
    Optional<CartItem> findById(@Param("cartItemId") Long cartItemId);

    @Insert("insert into cart_item(member_id, product_id, quantity) " +
            "value(#{memberId}, #{productId}, #{quantity})")
    void save(
            @Param("memberId") Long memberId,
            @Param("productId") Long productId,
            @Param("quantity") int quantity
    );

    @Select("select * from cart_item " +
            "where member_id = #{memberId} " +
            "and product_id = #{productId}")
    Optional<CartItem> findByMemberIdAndProductId(
            @Param("memberId") Long memberId,
            @Param("productId") Long productId
    );

    @Update("update cart_item set quantity = #{quantity} " +
            "where id = #{cartItemId}")
    void updateQuantity(
            @Param("quantity") int quantity,
            @Param("cartItemId") Long cartItemId
    );

    @Select("select p.id, p.name, p.price, c.quantity " +
            "from cart_item as c inner join product p " +
            "on c.product_id = p.id " +
            "where c.member_id = #{memberId}")
    List<CartItemDto> findMyCartItems(@Param("memberId") Long memberId);

    @Delete("delete from cart_item " +
            "where member_id = #{memberId}")
    void deleteByMemberId(@Param("memberId") Long memberId);

    @Delete("delete from cart_item " +
            "where id = #{cartItemId}")
    void deleteById(@Param("cartItemId") Long cartItemId);
}
