package com.project.taskapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TaskappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskappApplication.class, args);
	}

}
