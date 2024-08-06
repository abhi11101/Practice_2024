package com.abhi.office.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OfficeDTO {

    @Positive(message = "Episode number cannot be negative")
    private int episodeNumber;

    @NotEmpty(message = "Episode name cannot be empty")
    private String episodeName;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Positive(message = "Season cannot be negative")
    private int season;

    @Positive(message = "Rating cannot be negative")
    @Max(value = 10, message = "Rating can be upto 10")
    private double rating;

}
