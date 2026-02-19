package com.example.Study.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySystemApplication.class, args);
	}

}
