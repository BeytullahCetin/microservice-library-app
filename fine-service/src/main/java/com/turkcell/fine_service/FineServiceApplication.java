package com.turkcell.fine_service;

import com.turkcell.fine_service.config.FineServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(FineServiceProperties.class)
public class FineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FineServiceApplication.class, args);
	}
}

