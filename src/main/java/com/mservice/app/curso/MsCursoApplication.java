package com.mservice.app.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCursoApplication.class, args);
	}

}
