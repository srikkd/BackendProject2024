package com.example.backendproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class })*/
//@EnableJpaAuditing
//@EnableAutoConfiguration
//@ComponentScan
//@EnableJpaRepositories
//@EntityScan
public class BackendprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendprojectApplication.class, args);
	}

}
