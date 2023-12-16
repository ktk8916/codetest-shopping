package com.outlier.shopping.order.service;

import com.outlier.shopping.cart.domain.dto.CartItemDto;
import com.outlier.shopping.cart.repository.CartMapper;
import com.outlier.shopping.order.domain.entity.Order;
import com.outlier.shopping.order.domain.entity.OrderItem;
import com.outlier.shopping.order.domain.response.BillResponse;
import com.outlier.shopping.order.repository.OrderItemMapper;
import com.outlier.shopping.order.repository.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final CartMapper cartMapper;

    @Transactional
    public BillResponse orderMyCartItems(Long memberId) {
        List<CartItemDto> myCartItems = cartMapper.findCartItemDtosByMemberId(memberId);

        int totalPrice = myCartItems.stream()
                .mapToInt(item -> item.price() * item.quantity())
                .sum();

        // 주문 생성
        Order order = Order.createOrder(memberId, totalPrice);
        orderMapper.save(order);

        // 주문 상품 등록
        myCartItems.forEach(item -> {
            OrderItem orderItem = OrderItem.createOrderItem(
                    order.getId(),
                    item.id(),
                    item.price(),
                    item.price());
            orderItemMapper.save(orderItem);
        });

        // 장바구니 초기화
        cartMapper.deleteByMemberId(memberId);

        return BillResponse.of(order.getId(), totalPrice,order.getCreatedAt());
    }
}
