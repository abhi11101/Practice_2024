package com.abhi.got.Repositories;

import com.abhi.got.Entity.GameOfThrones;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GameOfThronesRepo extends MongoRepository<GameOfThrones, String> {

    Optional<GameOfThrones> findByEpisodeNumber(int episodeNumber);

}
