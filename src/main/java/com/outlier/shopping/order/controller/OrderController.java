package com.outlier.shopping.order.controller;

import com.outlier.shopping.global.jwt.TokenInfo;
import com.outlier.shopping.order.domain.response.BillResponse;
import com.outlier.shopping.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/cart/my")
    public BillResponse orderMyCartItems(@AuthenticationPrincipal TokenInfo tokenInfo){
        return orderService.orderMyCartItems(tokenInfo.getId());
    }
}
