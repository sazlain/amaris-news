package com.news.amaris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmarisNewsApplication {

	private static final Logger logger = LoggerFactory.getLogger(AmarisNewsApplication.class);
	
	public static void main(String[] args) {
		logger.info("Starting aplication");
		SpringApplication.run(AmarisNewsApplication.class, args);
	}

}
