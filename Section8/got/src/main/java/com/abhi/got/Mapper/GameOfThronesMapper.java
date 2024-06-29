package com.abhi.got.Mapper;

import com.abhi.got.DTO.GameOfThronesDTO;
import com.abhi.got.Entity.GameOfThrones;

public class GameOfThronesMapper {

    public static GameOfThrones convertToGameOfThrones(GameOfThronesDTO gameOfThronesDTO, GameOfThrones gameOfThrones) {

        gameOfThrones.setEpisodeNumber(gameOfThronesDTO.getEpisodeNumber());
        gameOfThrones.setEpisodeName(gameOfThronesDTO.getEpisodeName());
        gameOfThrones.setDescription(gameOfThronesDTO.getDescription());
        gameOfThrones.setSeason(gameOfThronesDTO.getSeason());
        gameOfThrones.setRating(gameOfThronesDTO.getRating());
        gameOfThrones.setReleaseDate(gameOfThronesDTO.getReleaseDate());
        gameOfThrones.setCast(gameOfThronesDTO.getCast());

        return gameOfThrones;
    }

    public static GameOfThronesDTO convertToGameOfThronesDTO(GameOfThrones gameOfThrones, GameOfThronesDTO gameOfThronesDTO) {

        gameOfThronesDTO.setEpisodeNumber(gameOfThrones.getEpisodeNumber());
        gameOfThronesDTO.setEpisodeName(gameOfThrones.getEpisodeName());
        gameOfThronesDTO.setDescription(gameOfThrones.getDescription());
        gameOfThronesDTO.setSeason(gameOfThrones.getSeason());
        gameOfThronesDTO.setRating(gameOfThrones.getRating());
        gameOfThronesDTO.setReleaseDate(gameOfThrones.getReleaseDate());
        gameOfThronesDTO.setCast(gameOfThrones.getCast());

        return gameOfThronesDTO;
    }
}
