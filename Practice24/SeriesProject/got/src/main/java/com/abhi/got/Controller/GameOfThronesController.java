package com.abhi.got.Controller;

import com.abhi.got.Constants.GameOfThronesConstants;
import com.abhi.got.DTO.GameOfThronesDTO;
import com.abhi.got.DTO.ResponseDTO;
import com.abhi.got.Service.GameOfThronesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor @Validated
@RequestMapping(value = "/api/got/", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameOfThronesController {

    private final GameOfThronesService gameOfThronesService;


    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createEpisode(@RequestBody @Valid GameOfThronesDTO gameOfThronesDTO){

        gameOfThronesService.createEpisode(gameOfThronesDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO(GameOfThronesConstants.STATUS_201,GameOfThronesConstants.MESSAGE_201)
        );

    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateEpisode(@RequestBody @Valid GameOfThronesDTO gameOfThronesDTO){

        boolean updateFlag = gameOfThronesService.updateEpisode(gameOfThronesDTO);

        if (updateFlag){

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(GameOfThronesConstants.STATUS_200,GameOfThronesConstants.MESSAGE_200));
        }
        else {

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(GameOfThronesConstants.STATUS_417,GameOfThronesConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteEpisode(@RequestParam int episodeNumber){

        boolean deleteFlag = gameOfThronesService.deleteEpisode(episodeNumber);

        if (deleteFlag){

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(GameOfThronesConstants.STATUS_200,GameOfThronesConstants.MESSAGE_200));

        }else {

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(GameOfThronesConstants.STATUS_417,GameOfThronesConstants.MESSAGE_417_DELETE));

        }

    }

    @GetMapping("fetch")
    public ResponseEntity<GameOfThronesDTO> fetchEpisode(@RequestParam int episodeNumber){

        GameOfThronesDTO gameOfThronesDTO = gameOfThronesService.fetchEpisode(episodeNumber);

        return ResponseEntity.status(HttpStatus.OK).body(gameOfThronesDTO);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<GameOfThronesDTO>> fetchAllEpisodes(){

        List<GameOfThronesDTO> gameOfThronesDTOList = gameOfThronesService.getEpisodes();

        return ResponseEntity.status(HttpStatus.OK).body(gameOfThronesDTOList);

    }

}
