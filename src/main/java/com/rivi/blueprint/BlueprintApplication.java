package com.rivi.blueprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.rivi.blueprint.*")
@ComponentScan(basePackages = { "com.rivi.blueprint.*" })
@EntityScan("com.rivi.blueprint.*")
public class BlueprintApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueprintApplication.class, args);
	}

}
