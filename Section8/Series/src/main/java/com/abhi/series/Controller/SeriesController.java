package com.abhi.series.Controller;

import com.abhi.series.Constants.SeriesConstants;
import com.abhi.series.DTO.ResponseDTO;
import com.abhi.series.DTO.SeriesDTO;
import com.abhi.series.DTO.SeriesDetailsDTO;
import com.abhi.series.Service.SeriesDetailsService;
import com.abhi.series.Service.SeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api/series/",produces = MediaType.APPLICATION_JSON_VALUE)
public class SeriesController {

    private final SeriesService seriesService;
    private final SeriesDetailsService seriesDetailsService;


    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createSeries(@RequestBody @Valid SeriesDTO seriesDTO){

        seriesService.createSeries(seriesDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO(SeriesConstants.STATUS_201,SeriesConstants.MESSAGE_201)
        );

    }


    @GetMapping("fetch")
    public ResponseEntity<SeriesDTO> fetchByUniqueId(@RequestParam String uniqueId){

        SeriesDTO seriesDTO = seriesService.getSeries(uniqueId);

        return ResponseEntity.status(HttpStatus.OK).body(seriesDTO);

    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateSeries(@RequestBody @Valid SeriesDTO seriesDTO){

        boolean updateFlag = seriesService.updateSeries(seriesDTO);

        if (updateFlag){

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(SeriesConstants.STATUS_200,SeriesConstants.MESSAGE_200));

        }else {

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(SeriesConstants.STATUS_417,SeriesConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteSeries(@RequestParam String uniqueId){

        boolean deleteFlag = seriesService.deleteSeries(uniqueId);

        if (deleteFlag){

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(SeriesConstants.STATUS_200,SeriesConstants.MESSAGE_200));
        }else {

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(SeriesConstants.STATUS_417,SeriesConstants.MESSAGE_417_DELETE));
        }

    }

    @GetMapping("findAll")
    public ResponseEntity<List<SeriesDTO>> fetchAllSeries(){

        List<SeriesDTO> seriesDTOList = seriesService.getAllSeries();

        return ResponseEntity.status(HttpStatus.OK).body(seriesDTOList);
    }

    @GetMapping("fetchDetails")
    public ResponseEntity<Object> fetchSeriesDetails(@RequestParam String uniqueId){

        return ResponseEntity.status(HttpStatus.OK).body(seriesDetailsService.findAll(uniqueId));
    }


}
