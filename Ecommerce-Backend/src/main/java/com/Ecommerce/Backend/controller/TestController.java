package com.Ecommerce.Backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("api")
    public String get(){
        return "Backend is Running";
    }
}
