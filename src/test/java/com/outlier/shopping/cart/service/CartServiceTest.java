package com.outlier.shopping.cart.service;

import com.outlier.shopping.cart.domain.dto.CartItemDto;
import com.outlier.shopping.cart.domain.entity.CartItem;
import com.outlier.shopping.cart.domain.reqeust.ItemAddRequest;
import com.outlier.shopping.cart.domain.response.CartItemResponse;
import com.outlier.shopping.cart.exception.CartExceptionType;
import com.outlier.shopping.cart.repository.CartMapper;
import com.outlier.shopping.global.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class CartServiceTest {

    @Autowired
    private CartService cartService;
    @MockBean
    private CartMapper cartMapper;

    @DisplayName("장바구니에 상품을 추가한다.")
    @Test
    void addItem(){
        // given
        ItemAddRequest request = new ItemAddRequest(1L, 10);

        when(cartMapper.findByMemberIdAndProductId(1L, 1L))
                .thenReturn(Optional.empty());

        // when
        cartService.addItem(1L, request);

        // then
        verify(cartMapper, never()).updateQuantity(anyInt(), anyLong());
        verify(cartMapper).save(1L, 1L, 10);
    }

    @DisplayName("장바구니에 상품이 존재하면, 수량을 추가한다.")
    @Test
    void addExistItem(){
        // given
        ItemAddRequest request = new ItemAddRequest(1L, 10);

        CartItem cartItem = CartItem.builder()
                .memberId(1L)
                .productId(1L)
                .quantity(10)
                .build();

        when(cartMapper.findByMemberIdAndProductId(1L, 1L))
                .thenReturn(Optional.of(cartItem));

        // when
        cartService.addItem(1L, request);

        // then
        verify(cartMapper).updateQuantity(eq(20), any());
    }

    @DisplayName("내 장바구니 상품들을 조회한다.")
    @Test
    void getMyCartItems(){
        // given
        List<CartItemDto> cartItems = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            cartItems.add(
                    CartItemDto.builder()
                            .id((long) i)
                            .name(String.valueOf(i))
                            .price(i * 1000)
                            .quantity(i)
                            .build()
            );
        }

        when(cartMapper.findMyCartItems(1L))
                .thenReturn(cartItems);

        // when
        CartItemResponse myCartItems = cartService.getMyCartItems(1L);

        // then
        assertThat(myCartItems.items()).hasSize(3);
        assertThat(myCartItems.totalPrice()).isEqualTo(14000);

    }

    @DisplayName("id로 장바구니 상품을 삭제한다.")
    @Test
    void deleteById(){
        // given
        CartItem cartItem = CartItem.builder()
                .memberId(1L)
                .productId(1L)
                .quantity(10)
                .build();

        when(cartMapper.findById(any()))
                .thenReturn(Optional.of(cartItem));

        // when
        cartService.deleteById(1L, 1L);

        // then
        verify(cartMapper).deleteById(1L);
    }

    @DisplayName("장바구니 상품 삭제 시, 주인이 아니면 예외가 발생한다.")
    @Test
    void deleteByIdInvalidCartOwner(){
        // given
        CartItem cartItem = CartItem.builder()
                .memberId(1L)
                .productId(1L)
                .quantity(10)
                .build();

        when(cartMapper.findById(any()))
                .thenReturn(Optional.of(cartItem));

        Long anotherMemberId = 2L;

        // when, then
        assertThatThrownBy(() -> cartService.deleteById(anotherMemberId, 1L))
                .isInstanceOf(CustomException.class)
                .hasMessage(CartExceptionType.INVALID_CART_OWNER.getMessage());
    }

    @DisplayName("장바구니 상품 삭제 시, 상품이 존재하지 않으면 예외가 발생한다.")
    @Test
    void deleteByIdNotExistItem(){
        // given
        when(cartMapper.findById(1L))
                .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(()->cartService.deleteById(1L, 1L))
                .isInstanceOf(CustomException.class)
                .hasMessage(CartExceptionType.CART_ITEM_NOT_FOUND.getMessage());
    }

}