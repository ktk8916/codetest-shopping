package com.outlier.shopping.auth.service;

import com.outlier.shopping.member.domain.entity.Member;
import com.outlier.shopping.auth.domain.request.LoginRequest;
import com.outlier.shopping.auth.domain.request.SignupRequest;
import com.outlier.shopping.auth.domain.response.LoginResponse;
import com.outlier.shopping.global.exception.CustomException;
import com.outlier.shopping.auth.exception.AuthExceptionType;
import com.outlier.shopping.member.repository.MemberMapper;
import com.outlier.shopping.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberMapper memberMapper;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse signup(SignupRequest request){
        // username 은 중복 허용 x
        Member member = Member.createMember(
                request.username(),
                passwordEncoder.encode(request.password()),
                request.nickname()
        );

        try {
            memberMapper.save(member);
        } catch (DuplicateKeyException e) {
            throw new CustomException(AuthExceptionType.INVALID_USERNAME, e);
        }

        String token = jwtProvider.generateToken(member);

        return LoginResponse.of(token);
    }

    public LoginResponse login(LoginRequest request) {
        Member member = memberMapper.findByUsername(request.username())
                .orElseThrow(() -> new CustomException(AuthExceptionType.INVALID_USERNAME));

        if(!isValidMemberPassword(request.password(), member.getPassword())) {
            throw new CustomException(AuthExceptionType.INVALID_PASSWORD);
        }

        String token = jwtProvider.generateToken(member);

        return LoginResponse.of(token);
    }

    private boolean isValidMemberPassword(String requestPassword, String memberPassword) {
        return passwordEncoder.matches(requestPassword, memberPassword);
    }
}
