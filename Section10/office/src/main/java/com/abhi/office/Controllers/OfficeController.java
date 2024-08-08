package com.abhi.office.Controllers;

import com.abhi.office.Constants.OfficeConstants;
import com.abhi.office.DTO.OfficeDTO;
import com.abhi.office.DTO.ResponseDTO;
import com.abhi.office.Service.OfficeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/office/", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class OfficeController {

    private OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createOffice(@Valid @RequestBody OfficeDTO officeDTO) {

        officeService.createOffice(officeDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(OfficeConstants.STATUS_201,OfficeConstants.MESSAGE_201));

    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateEpisode(@Valid @RequestBody OfficeDTO officeDTO) {

        boolean updateFlag = officeService.updateOffice(officeDTO);

        if (updateFlag){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(OfficeConstants.STATUS_200,OfficeConstants.MESSAGE_200));
        }else {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(OfficeConstants.STATUS_417,OfficeConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteEpisode(@RequestParam int episodeNumber){

        boolean deleteFlag = officeService.deleteOffice(episodeNumber);

        if(deleteFlag){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(OfficeConstants.STATUS_200,OfficeConstants.MESSAGE_200));
        }else {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(OfficeConstants.STATUS_417,OfficeConstants.MESSAGE_417_DELETE));
        }

    }

    @GetMapping("fetch")
    public ResponseEntity<OfficeDTO> findEpisode(@RequestParam int episodeNumber){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(officeService.fetchOffice(episodeNumber));

    }

    @GetMapping("findAll")
    public ResponseEntity<List<OfficeDTO>> findAllEpisodes(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(officeService.fetchAllOffice());
    }

}
