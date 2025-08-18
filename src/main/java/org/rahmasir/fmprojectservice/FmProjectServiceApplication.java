package org.rahmasir.fmprojectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main entry point for the Project Service application.
 */
@SpringBootApplication
@EnableCaching
public class FmProjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmProjectServiceApplication.class, args);
	}

}
