package com.mservice.app.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.mservice.commons.alumnos.models.entity","com.mservice.app.curso.models.entity",
			 "com.mservice.commons.examenes.models.entity"})
public class MsCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCursoApplication.class, args);
	}

}
