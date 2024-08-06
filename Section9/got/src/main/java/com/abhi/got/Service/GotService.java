package com.abhi.got.Service;

import com.abhi.got.DTO.GameOfThronesDTO;

import java.util.List;

public interface GotService {

    void createEpisode(GameOfThronesDTO gameOfThronesDTO);

    boolean updateEpisode(GameOfThronesDTO gameOfThronesDTO);

    boolean deleteEpisode(int episodeNumber);

    GameOfThronesDTO getEpisode(int episodeNumber);

    List<GameOfThronesDTO> getAllEpisodes();

}
