package com.abhi.friends.DTO;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "friends")
public record FriendsContactDetailsDTO(String message, Map<String, String> contactDetails) {
}
