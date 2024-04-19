package com.github.mohajel.IE.CA4.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class C {

    @GetMapping("/")
    String index() {
        return "Hello, World!";
    }

}