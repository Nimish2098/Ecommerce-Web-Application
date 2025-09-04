package com.Ecommerce.Backend.controller;

import com.Ecommerce.Backend.dto.AuthResponse;
import com.Ecommerce.Backend.dto.LoginRequest;
import com.Ecommerce.Backend.dto.RegisterRequest;
import com.Ecommerce.Backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
