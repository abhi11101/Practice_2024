package com.abhi.series.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OfficeDTO {

    @Positive(message = "Episode Number cannot be negative or zero")
    private Integer episodeNumber;

    @NotEmpty(message = "Name cannot be empty")
    private String episodeName;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Positive(message = "Season cannot be negative or zero")
    private Integer season;

    @Positive(message = "rating cannot be negative or zero")
    @Max(value = 10,message = "Rating can be max upto 10")
    private Double rating;

}
