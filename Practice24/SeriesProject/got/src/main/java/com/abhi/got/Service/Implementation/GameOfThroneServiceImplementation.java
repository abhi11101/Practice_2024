package com.abhi.got.Service.Implementation;

import com.abhi.got.DTO.GameOfThronesDTO;
import com.abhi.got.Entity.GameOfThrones;
import com.abhi.got.ExceptionHandling.EpisodeAlreadyExistException;
import com.abhi.got.ExceptionHandling.ResourceNotFoundException;
import com.abhi.got.Mapper.GameOfThronesMapper;
import com.abhi.got.Repositories.GameOfThronesRepo;
import com.abhi.got.Service.GameOfThronesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameOfThroneServiceImplementation implements GameOfThronesService {

    private final GameOfThronesRepo gameOfThronesRepo;

    @Override
    public void createEpisode(GameOfThronesDTO gameOfThronesDTO){

        Optional<GameOfThrones> gameOfThronesOptional = gameOfThronesRepo.findByEpisodeNumber(gameOfThronesDTO.getEpisodeNumber());

        if (gameOfThronesOptional.isPresent()){

            throw new EpisodeAlreadyExistException(""+gameOfThronesDTO.getEpisodeNumber());
        }

        GameOfThrones gameOfThrones = GameOfThronesMapper.convertToGameOfThrones(gameOfThronesDTO, new GameOfThrones());
        System.out.println(gameOfThrones);
        gameOfThronesRepo.save(gameOfThrones);

    }

    @Override
    public boolean updateEpisode(GameOfThronesDTO gameOfThronesDTO){

        GameOfThrones gameOfThrones = gameOfThronesRepo.findByEpisodeNumber(gameOfThronesDTO.getEpisodeNumber()).orElseThrow(
                () -> new ResourceNotFoundException("episode", "number", ""+gameOfThronesDTO.getEpisodeNumber())
        );

        GameOfThrones savedGameOfThrones = GameOfThronesMapper.convertToGameOfThrones(gameOfThronesDTO,gameOfThrones);
        gameOfThronesRepo.save(savedGameOfThrones);

        return true;
    }

    @Override
    public boolean deleteEpisode(int episodeNumber){

        GameOfThrones gameOfThrones = gameOfThronesRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                () -> new ResourceNotFoundException("episode", "number", ""+episodeNumber)
        );

        gameOfThronesRepo.deleteById(gameOfThrones.getId());

        return true;
    }

    @Override
    public GameOfThronesDTO fetchEpisode(int episodeNumber){

        GameOfThrones gameOfThrones = gameOfThronesRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                () -> new ResourceNotFoundException("episode", "number", ""+episodeNumber)
        );

        GameOfThronesDTO gameOfThronesDTO = GameOfThronesMapper.convertToGameOfThronesDTO(gameOfThrones, new GameOfThronesDTO());

        return gameOfThronesDTO;
    }

    @Override
    public List<GameOfThronesDTO> getEpisodes() {
        List<GameOfThrones> gameOfThronesList = gameOfThronesRepo.findAll();
        List<GameOfThronesDTO> gameOfThronesDTOList = new ArrayList<>();

        gameOfThronesList.forEach(
                (gameOfThrones) -> {
                    GameOfThronesDTO gameOfThronesDTO = GameOfThronesMapper.convertToGameOfThronesDTO(gameOfThrones, new GameOfThronesDTO());
                    gameOfThronesDTOList.add(gameOfThronesDTO);
                }
        );

        return gameOfThronesDTOList;
    }

}





















