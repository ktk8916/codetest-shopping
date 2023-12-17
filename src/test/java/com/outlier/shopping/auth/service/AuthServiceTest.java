package com.outlier.shopping.auth.service;

import com.outlier.shopping.member.domain.entity.Member;
import com.outlier.shopping.auth.domain.request.LoginRequest;
import com.outlier.shopping.auth.domain.request.SignupRequest;
import com.outlier.shopping.auth.domain.response.LoginResponse;
import com.outlier.shopping.auth.exception.AuthExceptionType;
import com.outlier.shopping.member.repository.MemberMapper;
import com.outlier.shopping.global.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @MockBean
    private MemberMapper memberMapper;

    @DisplayName("회원가입을 한다.")
    @Test
    void signup(){
        // given
        SignupRequest request = new SignupRequest("username", "password", "nickname");

        // when
        authService.signup(request);

        // then
        verify(memberMapper, atLeastOnce())
                .save(anyString(), anyString(), anyString());
    }

    @DisplayName("중복된 username으로 회원가입 시 예외가 발생한다.")
    @Test
    void signupDuplicateMemberUsername(){
        // given
        doThrow(DuplicateKeyException.class)
                .when(memberMapper)
                .save(anyString(), anyString(), anyString());

        SignupRequest request = new SignupRequest("username", "password", "nickname");

        // when, then
        assertThatThrownBy(()->authService.signup(request))
                .isInstanceOf(CustomException.class)
                .hasMessage(AuthExceptionType.INVALID_USERNAME.getMessage());
    }

    @DisplayName("로그인 후 토큰을 발급받는다.")
    @Test
    void login(){
        // given
        Member member = Member.builder()
                .username("username")
                .password(passwordEncoder.encode("password"))
                .nickname("nickname")
                .build();

        when(memberMapper.findByUsername("username"))
                .thenReturn(Optional.ofNullable(member));

        LoginRequest request = new LoginRequest("username", "password");

        // when
        LoginResponse response = authService.login(request);

        // then
        assertThat(response.accessToken()).isNotNull();
    }

    @DisplayName("로그인 시 username이 존재하지 않으면 예외가 발생한다.")
    @Test
    void loginWithInvalidUsername(){
        // given
        when(memberMapper.findByUsername(anyString()))
                .thenReturn(Optional.empty());

        LoginRequest request = new LoginRequest("username", "password");

        // when, then
        assertThatThrownBy(()->authService.login(request))
                .isInstanceOf(CustomException.class)
                .hasMessage(AuthExceptionType.INVALID_USERNAME.getMessage());
    }

    @DisplayName("로그인 시 password가 다르면 예외가 발생한다.")
    @Test
    void loginWithInvalidPassword(){
        // given
        Member member = Member.builder()
                .username("username")
                .password("password")
                .nickname("nickname")
                .build();

        when(memberMapper.findByUsername("username"))
                .thenReturn(Optional.of(member));

        LoginRequest request = new LoginRequest("username", "invalid password");

        // when, then
        assertThatThrownBy(()->authService.login(request))
                .isInstanceOf(CustomException.class)
                .hasMessage(AuthExceptionType.INVALID_PASSWORD.getMessage());
    }

}