package com.SpringBoot.SpringSecutiryBasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
/* @EnableWebSecurity(debug = true) */
public class SpringSecutiryBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecutiryBasicsApplication.class, args);
	}

}
