package com.abhi.series.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SeriesDTO {

    @NotEmpty(message = "Series Name cannot be null or empty")
    private String name;

    @NotEmpty(message = "Series Description cannot be null or empty")
    private String description;

    @NotEmpty(message = "Series Genre cannot be null or empty")
    private String genre;

    @Positive()
    @Max(2100)
    private int releaseYear;

    @NotEmpty(message = "Unique Number can not be a null or empty")
    private String uniqueId;


}
