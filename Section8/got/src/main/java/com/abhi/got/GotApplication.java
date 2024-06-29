package com.abhi.got;

import com.abhi.got.DTO.GotContactDetailsDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = GotContactDetailsDTO.class)
public class GotApplication {

	public static void main(String[] args) {
		SpringApplication.run(GotApplication.class, args);
	}

}
