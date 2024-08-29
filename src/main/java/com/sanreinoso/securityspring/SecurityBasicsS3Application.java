package com.sanreinoso.securityspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sanreinoso.securityspring.section3")
public class SecurityBasicsS3Application {

	public static void main(String[] args) {

		SpringApplication.run(SecurityBasicsS3Application.class, args);
		System.out.println("Application Section 3 started...");
	}

}
