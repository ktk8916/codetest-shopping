package com.outlier.shopping.order.repository;

import com.outlier.shopping.order.domain.dto.OrderReceiptDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderQueryMapper {

    List<OrderReceiptDto> findReceiptByMemberIdAndCondition(Long memberId, int limit, int offset);
    int findReceiptTotalSizeByMemberId(Long memberId);
}
