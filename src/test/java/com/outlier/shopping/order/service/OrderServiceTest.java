package com.outlier.shopping.order.service;

import com.outlier.shopping.cart.domain.entity.CartItem;
import com.outlier.shopping.cart.repository.CartMapper;
import com.outlier.shopping.global.exception.CustomException;
import com.outlier.shopping.order.domain.dto.OrderReceiptDto;
import com.outlier.shopping.order.domain.response.BillResponse;
import com.outlier.shopping.order.domain.response.OrderHistoryResponse;
import com.outlier.shopping.order.exception.OrderExceptionType;
import com.outlier.shopping.order.repository.OrderItemMapper;
import com.outlier.shopping.order.repository.OrderMapper;
import com.outlier.shopping.order.repository.OrderQueryMapper;
import com.outlier.shopping.product.domain.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderMapper orderMapper;
    @MockBean
    private OrderItemMapper orderItemMapper;
    @MockBean
    private OrderQueryMapper orderQueryMapper;
    @MockBean
    private CartMapper cartMapper;

    @DisplayName("장바구니 상품들로 주문을 생성한다.")
    @Test
    void createOrder(){
        // given
        List<CartItem> cartItems = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            Product product = Product.builder().price(i * 1000).build();
            CartItem cartItem = CartItem.builder().product(product).quantity(i).build();
            cartItems.add(cartItem);
        }

        when(cartMapper.findByMemberIdFetchProduct(1L))
                .thenReturn(cartItems);

        // when
        BillResponse billResponse = orderService.orderMyCartItems(1L);

        // then
        verify(orderMapper, atLeastOnce()).save(any());
        verify(orderItemMapper, times(3)).save(any());
        assertThat(billResponse.totalPrice()).isEqualTo(14000);
    }

    @DisplayName("주문 시 주문 목록이 비어있으면 예외가 발생한다.")
    @Test
    void orderEmptyCartList(){
        // given
        List<CartItem> emptyOrderList= List.of();
        when(cartMapper.findByMemberIdFetchProduct(anyLong()))
                .thenReturn(emptyOrderList);

        // when, then
        assertThatThrownBy(() -> orderService.orderMyCartItems(1L))
                .isInstanceOf(CustomException.class)
                .hasMessage(OrderExceptionType.EMPTY_ORDER_LIST.getMessage());
    }

    @DisplayName("내 주문내역들을 조회한다.")
    @Test
    void getMyOrderDetails(){
        // given
        int page = 1;
        int size = 10;

        List<OrderReceiptDto> orders = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            OrderReceiptDto dto = OrderReceiptDto.builder().build();
            orders.add(dto);
        }

        when(orderQueryMapper.findReceiptByMemberIdAndCondition(1L, 10, 10))
                .thenReturn(orders);

        when(orderQueryMapper.findReceiptTotalSizeByMemberId(1L))
                .thenReturn(13);

        // when
        OrderHistoryResponse response = orderService.getMyOrderDetails(1L, page, size);

        // then
        verify(orderQueryMapper).findReceiptByMemberIdAndCondition(1L, 10, 10);
        assertThat(response.orders()).hasSize(3);
        assertThat(response.totalSize()).isEqualTo(13);
    }
}