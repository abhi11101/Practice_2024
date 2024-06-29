package com.abhi.got.DTO;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "got")
public record GotContactDetailsDTO(String message, Map<String,String> contactDetails) {
}
