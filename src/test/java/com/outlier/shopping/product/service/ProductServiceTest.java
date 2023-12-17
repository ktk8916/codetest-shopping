package com.outlier.shopping.product.service;

import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import com.outlier.shopping.product.domain.response.ProductSearchResponse;
import com.outlier.shopping.product.repository.ProductQueryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @MockBean
    private ProductQueryMapper productQueryMapper;

    @DisplayName("조건에 따라 상품 정보를 조회한다.")
    @Test
    void searchByCondition(){
        // given
        int page = 1;
        int size = 10;

        List<ProductThumbnailDto> products = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ProductThumbnailDto dto = ProductThumbnailDto.builder().build();
            products.add(dto);
        }

        when(productQueryMapper.findProductThumbnailByCondition("keyword", 10, 10))
                .thenReturn(products);

        when(productQueryMapper.findTotalSizeByCondition("keyword"))
                .thenReturn(10);

        // when
        ProductSearchResponse response = productService.searchByCondition("keyword", page, size);

        // then
        verify(productQueryMapper).findProductThumbnailByCondition("keyword", 10, 10);
        assertThat(response.products()).hasSize(3);
    }
}