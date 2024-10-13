package com.sanreinoso.securityspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getAccountDetails() {

        return "Balance account details";
    }
}
