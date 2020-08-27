package com.tcs.cd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerdataApplication {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerdataApplication.class);
	
	public static void main(String[] args) {
		
		LOG.info("CustomerdataApplication Main invoked");
		
		SpringApplication.run(CustomerdataApplication.class, args);
	}

}
