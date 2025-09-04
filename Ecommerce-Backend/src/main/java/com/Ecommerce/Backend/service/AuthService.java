package com.Ecommerce.Backend.service;
//
//import com.Ecommerce.Backend.dto.AuthResponse;
//import com.Ecommerce.Backend.dto.RegisterRequest;
//import com.Ecommerce.Backend.dto.LoginRequest;
//import com.Ecommerce.Backend.repository.UserRepository;
//import com.Ecommerce.Backend.security.JwtUtil;
//import com.Ecommerce.Backend.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    public AuthResponse register(RegisterRequest request){
//        if(userRepository.existsByEmail(request.getEmail())){
//            throw new RuntimeException ("Email Already in use");
//        }
//
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
////        user.setRoles(Set.of(Role.USER));
//
//
//        String token = jwtUtil.generateToken(user.getEmail(),user.getRoles());
//        return new AuthResponse(token);
//    }
//    public AuthResponse login(LoginRequest request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail(), user.getRoles());
//        return new AuthResponse(token);
//    }
//}

import com.Ecommerce.Backend.dto.*;
import com.Ecommerce.Backend.entity.User;
import com.Ecommerce.Backend.repository.UserRepository;
import com.Ecommerce.Backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Ecommerce.Backend.security.SecurityConfig;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of("USER"))
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRoles());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRoles());
        return new AuthResponse(token);
    }
}
