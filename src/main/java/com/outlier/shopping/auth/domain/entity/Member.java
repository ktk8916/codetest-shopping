package com.outlier.shopping.auth.domain.entity;

import lombok.Getter;

@Getter
public class Member {

    private Long id;
    private String username;
    private String password;
    private String nickname;
}

