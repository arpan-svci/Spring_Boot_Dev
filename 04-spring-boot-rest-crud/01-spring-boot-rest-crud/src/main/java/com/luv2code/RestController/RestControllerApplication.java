package com.luv2code.RestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.luv2code.RestController.rest,com.luv2code.RestController.entity")
public class RestControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestControllerApplication.class, args);
	}

}
