package com.outlier.shopping.member.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private Long id;
    private String username;
    private String password;
    private String nickname;

    public static Member createMember(String username, String password, String nickname){
        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }

    @Builder
    public Member(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}

