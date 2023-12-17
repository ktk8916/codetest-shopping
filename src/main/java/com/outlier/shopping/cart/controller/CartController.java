package com.outlier.shopping.cart.controller;

import com.outlier.shopping.cart.domain.reqeust.ItemAddRequest;
import com.outlier.shopping.cart.domain.response.CartItemResponse;
import com.outlier.shopping.cart.service.CartService;
import com.outlier.shopping.global.jwt.TokenInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(
            @AuthenticationPrincipal TokenInfo tokenInfo,
            @RequestBody @Valid ItemAddRequest request
    ){
        cartService.addItem(tokenInfo.getId(), request);
    }

    @GetMapping("/items/my")
    public CartItemResponse getMyCartItems(@AuthenticationPrincipal TokenInfo tokenInfo){
        return cartService.getMyCartItems(tokenInfo.getId());
    }

    @DeleteMapping("/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @AuthenticationPrincipal TokenInfo tokenInfo,
            @PathVariable Long itemId
    ){
        cartService.deleteById(tokenInfo.getId(), itemId);
    }

    @DeleteMapping("/items/my")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllMyCartItems(@AuthenticationPrincipal TokenInfo tokenInfo){
        cartService.deleteAllMyCartItems(tokenInfo.getId());
    }
}
