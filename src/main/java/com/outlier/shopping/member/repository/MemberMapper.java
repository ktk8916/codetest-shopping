package com.outlier.shopping.member.repository;

import com.outlier.shopping.member.domain.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Optional<Member> findByUsername(@Param("username") String username);
}
