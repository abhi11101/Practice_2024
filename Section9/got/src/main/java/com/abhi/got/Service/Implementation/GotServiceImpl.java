package com.abhi.got.Service.Implementation;

import com.abhi.got.DTO.GameOfThronesDTO;
import com.abhi.got.Entity.GameOfThrones;
import com.abhi.got.ExceptionHandling.EpisodeAlreadyExistException;
import com.abhi.got.ExceptionHandling.ResourceNotFoundException;
import com.abhi.got.Mapper.GOTMapper;
import com.abhi.got.Repository.GotRepo;
import com.abhi.got.Service.GotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GotServiceImpl implements GotService {

    private GotRepo gotRepo;

    @Autowired
    public GotServiceImpl(GotRepo gotRepo) {
        this.gotRepo = gotRepo;
    }

    @Override
    public void createEpisode(GameOfThronesDTO gameOfThronesDTO) {

        Optional<GameOfThrones> gameOfThronesOptional = gotRepo.findByEpisodeNumber(gameOfThronesDTO.getEpisodeNumber());

        if (gameOfThronesOptional.isPresent()) {
            throw new EpisodeAlreadyExistException(gameOfThronesDTO.getEpisodeNumber()+"");
        }

        GameOfThrones gameOfThrones = GOTMapper.convertToGameOfThrones(gameOfThronesDTO, new GameOfThrones());

        gotRepo.save(gameOfThrones);


    }

    @Override
    public boolean updateEpisode(GameOfThronesDTO gameOfThronesDTO) {

        GameOfThrones gameOfThrones = gotRepo.findByEpisodeNumber(gameOfThronesDTO.getEpisodeNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Episode","number",gameOfThronesDTO.getEpisodeNumber()+"")
        );

        GameOfThrones updatedGOT = GOTMapper.convertToGameOfThrones(gameOfThronesDTO,gameOfThrones);
        gotRepo.save(updatedGOT);
        return true;
    }

    @Override
    public boolean deleteEpisode(int episodeNumber) {

        GameOfThrones gameOfThrones = gotRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Episode","number",episodeNumber+"")
        );

        gotRepo.delete(gameOfThrones);

        return true;
    }

    @Override
    public GameOfThronesDTO getEpisode(int episodeNumber) {

        GameOfThrones gameOfThrones = gotRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Episode","number",episodeNumber+"")
        );

        GameOfThronesDTO gameOfThronesDTO = GOTMapper.convertToGameOfThronesDTO(gameOfThrones,new GameOfThronesDTO());
        return gameOfThronesDTO;
    }

    @Override
    public List<GameOfThronesDTO> getAllEpisodes() {

        List<GameOfThrones> gameOfThrones = gotRepo.findAll();

        return gameOfThrones.stream()
                .map(
                        (got) -> GOTMapper.convertToGameOfThronesDTO(got,new GameOfThronesDTO())
                )
                .toList();


    }
}
