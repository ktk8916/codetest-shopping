package com.outlier.shopping.product.service;

import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
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
        productMapper.save(request.name(), request.price(), managerId);
    }

    public ProductSearchResponse searchByCondition(String keyword, int page, int size) {
        String searchKeyword = "%" + keyword + "%";
        List<ProductThumbnailDto> products = productMapper.findProductThumbnailByCondition(searchKeyword, size, page * size);
        int totalSize = productMapper.findTotalSizeByCondition(searchKeyword);
        return ProductSearchResponse.of(products, page, size, totalSize);
    }
}
