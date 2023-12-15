package com.outlier.shopping.product.service;

import com.outlier.shopping.product.domain.request.CreateProductRequest;
import com.outlier.shopping.product.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public void register(Long managerId, CreateProductRequest request){
        productMapper.save(request.name(), request.price(), managerId);
    }
}
