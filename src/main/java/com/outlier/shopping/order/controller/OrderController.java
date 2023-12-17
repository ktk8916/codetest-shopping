package com.outlier.shopping.order.controller;

import com.outlier.shopping.global.jwt.TokenInfo;
import com.outlier.shopping.order.domain.response.BillResponse;
import com.outlier.shopping.order.domain.response.OrderHistoryResponse;
import com.outlier.shopping.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/cart/my")
    @ResponseStatus(HttpStatus.CREATED)
    public BillResponse orderMyCartItems(@AuthenticationPrincipal TokenInfo tokenInfo){
        return orderService.orderMyCartItems(tokenInfo.getId());
    }

    @GetMapping("details/my")
    public OrderHistoryResponse getMyOrderDetails(
            @AuthenticationPrincipal TokenInfo tokenInfo,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ){
        return orderService.getMyOrderDetails(tokenInfo.getId(), page, size);
    }
}
