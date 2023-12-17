package com.outlier.shopping.product.controller;

import com.outlier.shopping.global.jwt.TokenInfo;
import com.outlier.shopping.product.domain.request.CreateProductRequest;
import com.outlier.shopping.product.domain.response.ProductSearchResponse;
import com.outlier.shopping.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(
            @AuthenticationPrincipal TokenInfo tokenInfo,
            @RequestBody @Valid CreateProductRequest request
    ){
        productService.register(tokenInfo.getId(), request);
    }

    @GetMapping
    public ProductSearchResponse searchByCondition(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ){
        return productService.searchByCondition(keyword, page, size);
    }
}
