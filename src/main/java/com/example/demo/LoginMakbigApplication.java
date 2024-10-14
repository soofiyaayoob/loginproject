package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginMakbigApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginMakbigApplication.class, args);
		 System.out.println("Java Version: " + System.getProperty("java.version"));
	
	}

}
