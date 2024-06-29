package com.abhi.office.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "office")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Office extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long episodeId;
    private int episodeNumber;
    private String episodeName;
    private String description;
    private int season;
    private double rating;

}
