package com.abhi.got.Controller;

import com.abhi.got.Constants.GOTConstants;
import com.abhi.got.DTO.GameOfThronesDTO;
import com.abhi.got.DTO.ResponseDTO;
import com.abhi.got.Service.GotService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/got/", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class GotController {

    private GotService gotService;

    @Autowired
    public GotController(GotService gotService) {
        this.gotService = gotService;
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createEpisode(@Valid @RequestBody GameOfThronesDTO gameOfThronesDTO){

        gotService.createEpisode(gameOfThronesDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(GOTConstants.STATUS_201,GOTConstants.MESSAGE_201));

    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateEpisode(@Valid @RequestBody GameOfThronesDTO gameOfThronesDTO){

        boolean updateFlag = gotService.updateEpisode(gameOfThronesDTO);
        if(updateFlag){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(GOTConstants.STATUS_200,GOTConstants.MESSAGE_200));
        }else {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(GOTConstants.STATUS_417,GOTConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> updateEpisode( @RequestParam int episodeNumber){

        boolean deleteFlag = gotService.deleteEpisode(episodeNumber);
        if(deleteFlag){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(GOTConstants.STATUS_200,GOTConstants.MESSAGE_200));
        }else {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(GOTConstants.STATUS_417,GOTConstants.MESSAGE_417_DELETE));
        }

    }

    @GetMapping("fetch")
    public ResponseEntity<GameOfThronesDTO> fetchEpisode(@RequestParam int episodeNumber){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gotService.getEpisode(episodeNumber));

    }

    @GetMapping("findAll")
    public ResponseEntity<List<GameOfThronesDTO>> fetchAllEpisodes(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gotService.getAllEpisodes());

    }

}
