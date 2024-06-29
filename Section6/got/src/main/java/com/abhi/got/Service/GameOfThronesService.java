package com.abhi.got.Service;

import com.abhi.got.DTO.GameOfThronesDTO;

import java.util.List;

public interface GameOfThronesService {

    void createEpisode(GameOfThronesDTO gameOfThronesDTO);

    boolean updateEpisode(GameOfThronesDTO gameOfThronesDTO);

    boolean deleteEpisode(int episodeNumber);

    GameOfThronesDTO fetchEpisode(int episodeNumber);

    List<GameOfThronesDTO> getEpisodes();

}
