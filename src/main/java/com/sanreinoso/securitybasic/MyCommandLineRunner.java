package com.sanreinoso.securitybasic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
