package com.outlier.shopping.basket.service;

import com.outlier.shopping.basket.domain.dto.BasketItemDto;
import com.outlier.shopping.basket.domain.entity.Basket;
import com.outlier.shopping.basket.domain.reqeust.ProductAddRequest;
import com.outlier.shopping.basket.domain.response.BasketResponse;
import com.outlier.shopping.basket.exception.BasketExceptionType;
import com.outlier.shopping.basket.repository.BasketMapper;
import com.outlier.shopping.global.exception.CustomException;
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
        List<BasketItemDto> basketProduct = basketMapper.findMyBasketItems(memberId);

        int totalPrice = basketProduct.stream()
                .mapToInt(product -> product.price() * product.quantity())
                .sum();

        return BasketResponse.of(basketProduct, totalPrice);
    }
    public void deleteById(Long memberId, Long basketId) {
        Basket basket = findById(basketId);

        if(!isValidBasketOwner(memberId, basket)){
            throw new CustomException(BasketExceptionType.INVALID_BASKET_OWNER);
        }

        basketMapper.deleteById(basketId);
    }

    public void deleteMyBasket(Long memberId) {
        basketMapper.deleteByMemberId(memberId);
    }

    private Basket findById(Long basketId){
        return basketMapper.findById(basketId)
                .orElseThrow(() -> new CustomException(BasketExceptionType.BASKET_NOT_FOUND));
    }

    private boolean isValidBasketOwner(Long memberId, Basket basket) {
        return basket.getMemberId().equals(memberId);
    }
}
