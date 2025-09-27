package com.sanreinoso.securityspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class SecurityBasicsApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecurityBasicsApplication.class, args);
		System.out.println("Security basics section 10 started...");
	}

}
