package com.outlier.shopping.product.service;

import com.outlier.shopping.member.domain.entity.Member;
import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import com.outlier.shopping.product.domain.entity.Product;
import com.outlier.shopping.product.domain.request.CreateProductRequest;
import com.outlier.shopping.product.domain.response.ProductSearchResponse;
import com.outlier.shopping.product.repository.ProductMapper;
import com.outlier.shopping.product.repository.ProductQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductQueryMapper productQueryMapper;

    public void register(Long managerId, CreateProductRequest request){
        Member manager = Member.fromId(managerId);

        Product product = Product.createProduct(
                request.name(),
                request.price(),
                manager
        );

        productMapper.save(product);
    }

    public ProductSearchResponse searchByCondition(String keyword, int page, int size) {
        int offset = page * size;
        List<ProductThumbnailDto> products = productQueryMapper.findProductThumbnailByCondition(keyword, size, offset);
        int totalSize = productQueryMapper.findTotalSizeByCondition(keyword);
        return ProductSearchResponse.of(products, page, size, totalSize);
    }
}
