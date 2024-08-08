package com.abhi.series.Controller;

import com.abhi.series.Constants.SeriesConstants;
import com.abhi.series.DTO.ResponseDTO;
import com.abhi.series.DTO.SeriesDTO;
import com.abhi.series.Service.SeriesDetailsService;
import com.abhi.series.Service.SeriesService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/series/", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class SeriesController {

    private SeriesService seriesService;
    private SeriesDetailsService seriesDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(SeriesController.class);

    @Autowired
    public SeriesController(SeriesService seriesService, SeriesDetailsService seriesDetailsService) {
        this.seriesService = seriesService;
        this.seriesDetailsService = seriesDetailsService;
    }


    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createSeries(@Valid @RequestBody SeriesDTO seriesDTO) {

        seriesService.createSeries(seriesDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(SeriesConstants.STATUS_201,SeriesConstants.MESSAGE_201));

    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateSeries(@Valid @RequestBody SeriesDTO seriesDTO) {

        boolean updateFlag = seriesService.updateSeries(seriesDTO);

        if (updateFlag){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(SeriesConstants.STATUS_200,SeriesConstants.MESSAGE_200));

        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(SeriesConstants.STATUS_417,SeriesConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("update")
    public ResponseEntity<ResponseDTO> deleteSeries(@RequestParam String uniqueId) {

        boolean deleteFlag = seriesService.deleteSeries(uniqueId);

        if (deleteFlag){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(SeriesConstants.STATUS_200,SeriesConstants.MESSAGE_200));

        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(SeriesConstants.STATUS_417,SeriesConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("fetch")
    public ResponseEntity<Object> fetchSeriesDetails(@RequestParam String uniqueId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(seriesDetailsService.getAllEpisodes(uniqueId));
    }

    @GetMapping("findAll")
    public ResponseEntity<List<SeriesDTO>> findAllSeries() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(seriesService.getAllSeries());
    }
}
