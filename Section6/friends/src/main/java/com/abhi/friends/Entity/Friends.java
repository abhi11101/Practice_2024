package com.abhi.friends.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Friends extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friends_id")
    private Long friendsId;

    @Column(name = "episode_number")
    private int episodeNumber;

    @Column(name = "episode_name")
    private String episodeName;

    @Column(name = "description")
    private String description;

    @Column(name = "season")
    private int season;

    @Column(name = "rating")
    private double rating;

}
