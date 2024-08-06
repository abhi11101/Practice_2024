package com.abhi.got.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "got")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class GameOfThrones {

    @Id
    private String id;
    private int episodeNumber;
    private String episodeName;
    private String description;
    private String releaseDate;
    private int season;
    private double rating;
    private String[] cast;

}
