package com.sanreinoso.securityspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SecurityBasicsApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecurityBasicsApplication.class, args);
		System.out.println("Application Banking 8 started...");
	}

}
