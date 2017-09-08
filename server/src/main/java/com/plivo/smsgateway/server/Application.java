package com.plivo.smsgateway.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author abhineet
 *
 */
@ComponentScan("com.plivo")
@EnableJpaRepositories("com.plivo.smsgateway.repo")
@EntityScan("com.plivo.smsgateway.domain")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
