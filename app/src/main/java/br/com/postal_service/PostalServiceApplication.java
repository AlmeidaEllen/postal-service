package br.com.postal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.ConfigurableApplicationContext;

@EnableAsync
@SpringBootApplication
@EnableJpaRepositories 
public class PostalServiceApplication {
	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(PostalServiceApplication.class, args);
	}

	public static void close(int code) {
		SpringApplication.exit(ctx, () -> code);
	}
}


