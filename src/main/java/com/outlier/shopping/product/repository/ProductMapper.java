package com.outlier.shopping.product.repository;

import com.outlier.shopping.product.domain.dto.ProductThumbnailDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("insert into product(name, price, manager_id) " +
            "value(#{name}, #{price}, #{managerId})")
    void save(
            @Param("name") String name,
            @Param("price") int price,
            @Param("managerId") Long managerId
    );

    @Select("select * " +
            "from product p " +
            "where p.name like #{keyword} " +
            "limit #{limit} " +
            "offset #{offset}")
    List<ProductThumbnailDto> findProductThumbnailByCondition(
        @Param("keyword") String keyword,
        @Param("limit") int limit,
        @Param("offset") int offset
    );

    @Select("select count(*) " +
            "from product p " +
            "where p.name like #{keyword} ")
    int findTotalSizeByCondition(@Param("keyword") String keyword);

}
