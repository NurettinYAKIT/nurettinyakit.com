package com.nurettinyakit.dockerexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hi")
    private String sayHi() {
        return "Hello";
    }

}
