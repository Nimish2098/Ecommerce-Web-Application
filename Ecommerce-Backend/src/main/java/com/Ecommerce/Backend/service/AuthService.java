package com.Ecommerce.Backend.service;

import com.Ecommerce.Backend.dto.AuthResponse;
import com.Ecommerce.Backend.dto.RegisterRequest;
import com.Ecommerce.Backend.repository.UserRepository;
import com.Ecommerce.Backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException ("Email Already in use");
        }

        User user = User.builder()
                .username(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of("USER"))
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(),user.getRoles());
        return new AuthResponse(token);
    }
}
