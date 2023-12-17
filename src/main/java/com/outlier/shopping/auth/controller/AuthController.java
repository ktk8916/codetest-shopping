package com.outlier.shopping.auth.controller;

import com.outlier.shopping.auth.domain.request.LoginRequest;
import com.outlier.shopping.auth.domain.request.SignupRequest;
import com.outlier.shopping.auth.domain.response.LoginResponse;
import com.outlier.shopping.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse signup(@RequestBody @Valid SignupRequest request){
        return authService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request){
        return authService.login(request);
    }
}
