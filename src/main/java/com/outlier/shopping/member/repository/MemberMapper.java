package com.outlier.shopping.member.repository;

import com.outlier.shopping.member.domain.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Insert("insert into member(username, password, nickname) " +
            "value(#{username}, #{password}, #{nickname})")
    void save(
            @Param("username") String username,
            @Param("password") String password,
            @Param("nickname") String nickname
    );

    Optional<Member> findByUsername(@Param("username") String username);
}
