package com.Ecommerce.Backend.security;

import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf()
                .disable()
                .authorizeHttpRequests(auth->
                        auth.requestMatches("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build()
    }
}
