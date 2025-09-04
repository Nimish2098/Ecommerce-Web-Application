package com.Ecommerce.Backend.controller;

import com.Ecommerce.Backend.entity.User;
import com.Ecommerce.Backend.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/users")
    public List<User> get(){
        return userRepository.findAll();
    }
}
