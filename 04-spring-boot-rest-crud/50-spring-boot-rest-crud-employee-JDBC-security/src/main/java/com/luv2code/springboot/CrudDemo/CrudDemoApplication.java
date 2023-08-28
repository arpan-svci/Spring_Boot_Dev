package com.luv2code.springboot.CrudDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.luv2code.springboot.crudDemo.dao,com.luv2code.springboot.crudDemo.entity,com.luv2code.springboot.crudDemo.rest,com.luv2code.springboot.crudDemo.security,com.luv2code.springboot.crudDemo.service")
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

}
