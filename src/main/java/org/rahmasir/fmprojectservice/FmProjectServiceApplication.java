package org.rahmasir.fmprojectservice;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main entry point for the Project Service application.
 */
@SpringBootApplication
@EnableCaching
@SecurityScheme(
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT"
)
public class FmProjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmProjectServiceApplication.class, args);
	}

}
