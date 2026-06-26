package com.dadaev.tea_social;

import com.dadaev.tea_social.Repository.ReviewRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeaSocialApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TeaSocialApplication.class, args);
	}
}



