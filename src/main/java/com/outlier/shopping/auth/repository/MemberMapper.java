package com.outlier.shopping.auth.repository;

import com.outlier.shopping.auth.domain.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from member where username = #{username}")
    Optional<Member> findByUsername(@Param("username") String username);
}
