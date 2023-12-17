package com.outlier.shopping.cart.service;

import com.outlier.shopping.cart.domain.dto.CartItemDto;
import com.outlier.shopping.cart.domain.entity.CartItem;
import com.outlier.shopping.cart.domain.reqeust.ItemAddRequest;
import com.outlier.shopping.cart.domain.response.CartItemResponse;
import com.outlier.shopping.cart.exception.CartExceptionType;
import com.outlier.shopping.cart.repository.CartMapper;
import com.outlier.shopping.global.exception.CustomException;
import com.outlier.shopping.member.domain.entity.Member;
import com.outlier.shopping.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    public void addItem(Long memberId, ItemAddRequest request) {
        Optional<CartItem> findCartItem = cartMapper.findByMemberIdAndProductId(memberId, request.productId());

        if(findCartItem.isEmpty()){
            CartItem addCartItem = CartItem.createCartItem(
                Member.fromId(memberId),
                Product.fromId(request.productId()),
                request.quantity()
            );

            cartMapper.save(addCartItem);
            return;
        }

        CartItem cartItem = findCartItem.get();
        int quantity = cartItem.getQuantity() + request.quantity();

        cartMapper.updateQuantityById(quantity, cartItem.getId());
    }

    public CartItemResponse getMyCartItems(Long memberId) {
        List<CartItem> cartItems = cartMapper.findByMemberIdFetchProduct(memberId);

        int totalPrice = cartItems.stream()
                .mapToInt(CartItem::getSumPrice)
                .sum();

        List<CartItemDto> items = cartItems.stream()
                .map(CartItemDto::fromEntity)
                .toList();

        return CartItemResponse.of(items, totalPrice);
    }

    public void deleteById(Long memberId, Long cartItemId) {
        CartItem cartItem = findById(cartItemId);

        if(!isValidCartOwner(memberId, cartItem)){
            throw new CustomException(CartExceptionType.INVALID_CART_OWNER);
        }

        cartMapper.deleteById(cartItemId);
    }

    public void deleteAllMyCartItems(Long memberId) {
        cartMapper.deleteByMemberId(memberId);
    }

    private CartItem findById(Long cartItemId){
        return cartMapper.findByIdFetchMember(cartItemId)
                .orElseThrow(() -> new CustomException(CartExceptionType.CART_ITEM_NOT_FOUND));
    }

    private boolean isValidCartOwner(Long memberId, CartItem cartItem) {
        return cartItem.getMember().getId().equals(memberId);
    }
}
