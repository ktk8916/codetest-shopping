package com.outlier.shopping.product.service;

import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import com.outlier.shopping.product.domain.entity.Product;
import com.outlier.shopping.product.domain.request.CreateProductRequest;
import com.outlier.shopping.product.domain.response.ProductSearchResponse;
import com.outlier.shopping.product.repository.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public void register(Long managerId, CreateProductRequest request){
        Product product = Product.createProduct(
                request.name(),
                request.price(),
                managerId
        );

        productMapper.save(product);
    }

    public ProductSearchResponse searchByCondition(String keyword, int page, int size) {
        List<ProductThumbnailDto> products = productMapper.findProductThumbnailByCondition(keyword, size, page * size);
        int totalSize = productMapper.findTotalSizeByCondition(keyword);
        return ProductSearchResponse.of(products, page, size, totalSize);
    }
}
