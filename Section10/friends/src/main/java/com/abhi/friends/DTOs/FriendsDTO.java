package com.abhi.friends.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FriendsDTO {

    @Positive(message = "Episode number cannot be negative")
    private Integer episodeNumber;

    @NotEmpty(message = "Episode name cannot be empty")
    private String episodeName;

    @NotEmpty(message = "Episode description cannot be empty")
    private String description;

    @Positive(message = "Season cannot be negative")
    private Integer season;

    @Positive(message = "Rating cannot be negative")
    @Max(value = 10, message = "Maximum rating can be 10")
    private Double rating;

}