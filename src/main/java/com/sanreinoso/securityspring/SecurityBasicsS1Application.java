package com.sanreinoso.securityspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sanreinoso.securityspring.section1")
public class SecurityBasicsS1Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityBasicsS1Application.class, args);
	}

}
