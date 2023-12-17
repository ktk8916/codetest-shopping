package com.outlier.shopping.order.service;

import com.outlier.shopping.order.domain.dto.OrderReceiptDto;
import com.outlier.shopping.order.domain.response.OrderHistoryResponse;
import com.outlier.shopping.order.repository.OrderQueryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderQueryMapper orderQueryMapper;

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