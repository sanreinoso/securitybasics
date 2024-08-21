package com.sanreinoso.securityspring.section1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String sayWelcome() {
        return "Invoke by Section 1 - Welcome to Spring Application with Security. :D";
    }
}
