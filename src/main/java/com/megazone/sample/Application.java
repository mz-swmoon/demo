package com.megazone.sample;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOGGER.info("SPRING VERSION: " + SpringVersion.getVersion());
		SpringApplication.run(Application.class, args);
	}
}
