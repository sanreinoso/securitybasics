package com.sanreinoso.securityspring.controller;

import com.sanreinoso.securityspring.model.Customer;
import com.sanreinoso.securityspring.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try {
            // Save the user to the database
            String hashPwd = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashPwd);
            Customer newCustomer = this.customerRepository.save(customer);

            if(newCustomer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
            } else {
                return ResponseEntity.badRequest().body("Error registering user");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Fatal: Error registering user");
        }
    }
}
