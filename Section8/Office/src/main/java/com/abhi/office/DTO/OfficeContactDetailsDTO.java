package com.abhi.office.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "office")
@Getter @Setter
public class OfficeContactDetailsDTO {

    private String message;
    private Map<String, String> contactDetails;

}
