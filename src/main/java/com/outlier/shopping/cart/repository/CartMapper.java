package com.outlier.shopping.cart.repository;

import com.outlier.shopping.cart.domain.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

    void save(CartItem cartItem);

    Optional<CartItem> findByMemberIdAndProductId(Long memberId, Long productId);

    Optional<CartItem> findByIdFetchMember(Long cartItemId);

    void updateQuantityById(int quantity, Long cartItemId);

    void deleteByMemberId(Long memberId);

    void deleteById(Long cartItemId);

    List<CartItem> findByMemberIdFetchProduct(Long memberId);
}
