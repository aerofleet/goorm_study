package com.goorm.mission0220;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Mission0220Application {

	public static void main(String[] args) {
		SpringApplication.run(Mission0220Application.class, args);
	}
}
