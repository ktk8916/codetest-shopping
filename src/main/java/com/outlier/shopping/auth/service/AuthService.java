package com.outlier.shopping.auth.service;

import com.outlier.shopping.auth.domain.request.SignupRequest;
import com.outlier.shopping.auth.exception.AuthExceptionType;
import com.outlier.shopping.auth.repository.MemberMapper;
import com.outlier.shopping.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    public void signup(SignupRequest request){
        try {
            memberMapper.save(
                    request.username(),
                    passwordEncoder.encode(request.password()),
                    request.nickname()
            );
        } catch (DuplicateKeyException e) {
            throw new CustomException(AuthExceptionType.INVALID_USERNAME, e);
        }
    }
}
