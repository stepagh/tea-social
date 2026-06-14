package com.dadaev.tea_social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeaSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeaSocialApplication.class, args);
	}

}
