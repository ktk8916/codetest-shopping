package com.outlier.shopping.cart.repository;

import com.outlier.shopping.cart.domain.dto.CartItemDto;
import com.outlier.shopping.cart.domain.entity.CartItem;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

    void save(CartItem cartItem);

    Optional<CartItem> findByMemberIdAndProductId(
            @Param("memberId") Long memberId,
            @Param("productId") Long productId
    );

    @Select("select * from cart_item " +
            "where id = #{cartItemId}")
    Optional<CartItem> findById(@Param("cartItemId") Long cartItemId);


    @Select("select p.id, p.name, p.price, c.quantity " +
            "from cart_item as c inner join product p " +
            "on c.product_id = p.id " +
            "where c.member_id = #{memberId}")
    List<CartItemDto> findCartItemDtosByMemberId(@Param("memberId") Long memberId);

    @Select("select * from cart_item " +
            "where member_id = #{memberId}")
    List<CartItem> findByMemberId(@Param("memberId") Long memberId);



    @Update("update cart_item set quantity = #{quantity} " +
            "where id = #{cartItemId}")
    void updateQuantity(
            @Param("quantity") int quantity,
            @Param("cartItemId") Long cartItemId
    );

    @Delete("delete from cart_item " +
            "where member_id = #{memberId}")
    void deleteByMemberId(@Param("memberId") Long memberId);

    @Delete("delete from cart_item " +
            "where id = #{cartItemId}")
    void deleteById(@Param("cartItemId") Long cartItemId);
}
