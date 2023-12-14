package com.outlier.shopping.auth.controller;

import com.outlier.shopping.auth.domain.request.LoginRequest;
import com.outlier.shopping.auth.domain.request.SignupRequest;
import com.outlier.shopping.auth.domain.response.LoginResponse;
import com.outlier.shopping.auth.service.AuthService;
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
    public void signup(@RequestBody SignupRequest request){
        authService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
