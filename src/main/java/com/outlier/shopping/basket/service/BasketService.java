package com.outlier.shopping.basket.service;

import com.outlier.shopping.basket.domain.dto.BasketProductDto;
import com.outlier.shopping.basket.domain.entity.Basket;
import com.outlier.shopping.basket.domain.reqeust.ProductAddRequest;
import com.outlier.shopping.basket.domain.response.BasketResponse;
import com.outlier.shopping.basket.repository.BasketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketMapper basketMapper;

    public void addProduct(Long memberId, ProductAddRequest request) {
        Optional<Basket> findBasket = basketMapper.findByMemberIdAndProductId(memberId, request.productId());

        if(findBasket.isEmpty()){
            basketMapper.save(memberId, request.productId(), request.quantity());
            return;
        }

        Basket basket = findBasket.get();
        int quantity = basket.getQuantity() + request.quantity();

        basketMapper.updateQuantity(quantity, basket.getId());
    }

    public BasketResponse getMyBasket(Long memberId) {
        List<BasketProductDto> basketProduct = basketMapper.findMyBasketProduct(memberId);

        int totalPrice = basketProduct.stream()
                .mapToInt(product -> product.price() * product.quantity())
                .sum();

        return BasketResponse.of(basketProduct, totalPrice);
    }
}
