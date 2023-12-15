package com.outlier.shopping.basket.controller;

import com.outlier.shopping.basket.domain.reqeust.ProductAddRequest;
import com.outlier.shopping.basket.domain.response.BasketResponse;
import com.outlier.shopping.basket.service.BasketService;
import com.outlier.shopping.global.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/basket")
public class BasketController {

    private final BasketService basketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(
            @AuthenticationPrincipal TokenInfo tokenInfo,
            @RequestBody ProductAddRequest request
    ){
        basketService.addProduct(tokenInfo.getId(), request);
    }

    @GetMapping("/my")
    public BasketResponse getMyBasket(@AuthenticationPrincipal TokenInfo tokenInfo){
        return basketService.getMyBasket(tokenInfo.getId());
    }

    @DeleteMapping("/{basketId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @AuthenticationPrincipal TokenInfo tokenInfo,
            @PathVariable Long basketId
    ){
        basketService.deleteById(tokenInfo.getId(), basketId);
    }

    @DeleteMapping("/my")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyBasket(@AuthenticationPrincipal TokenInfo tokenInfo){
        basketService.deleteMyBasket(tokenInfo.getId());
    }
}
