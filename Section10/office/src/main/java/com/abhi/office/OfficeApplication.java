package com.abhi.office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareRef")
public class OfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfficeApplication.class, args);
	}

}
