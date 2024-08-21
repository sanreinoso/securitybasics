package com.sanreinoso.securityspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sanreinoso.securityspring.section2")
public class SecurityBasicsS2Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityBasicsS2Application.class, args);
	}

}
