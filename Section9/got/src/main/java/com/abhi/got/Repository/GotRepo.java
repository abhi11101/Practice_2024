package com.abhi.got.Repository;

import com.abhi.got.Entity.GameOfThrones;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GotRepo extends MongoRepository<GameOfThrones, String> {

    Optional<GameOfThrones> findByEpisodeNumber(int episodeNumber);

}
