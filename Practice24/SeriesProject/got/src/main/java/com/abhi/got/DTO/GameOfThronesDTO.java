package com.abhi.got.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameOfThronesDTO {

    @Positive(message = "Episode number cannot be negative or zero")
    private int episodeNumber;

    @NotEmpty(message = "Episode name cannot be empty")
    private String episodeName;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Release date cannot be empty")
    private String releaseDate;

    @Positive(message = "Season cannot be negative or zero")
    private int season;

    @Positive(message = "Rating cannot be negative or zero")
    @Max(value = 10, message = "Maximum rating is 10")
    private double rating;

    private String[] cast;

}
