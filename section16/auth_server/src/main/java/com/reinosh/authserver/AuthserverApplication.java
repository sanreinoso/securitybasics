package com.reinosh.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthserverApplication {

	public static void main(String[] args) {

		SpringApplication.run(AuthserverApplication.class, args);
		System.out.println("Auth Server is running...");
	}

}
