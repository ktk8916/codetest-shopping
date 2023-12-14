package com.outlier.shopping.auth.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private Long id;
    private String username;
    private String password;
    private String nickname;

    @Builder
    public Member(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}

