package com.abhi.office.DTO;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "office")
public record OfficeContactDetailsDTO(String message, Map<String, String> contactDetails) {
}
