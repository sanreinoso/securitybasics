package com.sanreinoso.securityspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sanreinoso.securityspring.section4")
public class SecurityBasicsS4Application {

	public static void main(String[] args) {

		SpringApplication.run(SecurityBasicsS4Application.class, args);
		System.out.println("Application Section 4 started...");
	}

}
