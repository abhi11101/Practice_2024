package com.abhi.office.Controllers;

import com.abhi.office.Constants.OfficeConstants;
import com.abhi.office.DTO.OfficeContactDetailsDTO;
import com.abhi.office.DTO.OfficeDTO;
import com.abhi.office.DTO.ResponseDTO;
import com.abhi.office.Services.OfficeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/office/",produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;
    private final Environment environment;
    private final OfficeContactDetailsDTO officeContactDetailsDTO;

    @Value("${build.version}")
    private String buildVersion;


    @GetMapping("findAll")
    public ResponseEntity<List<OfficeDTO>> findAll(){

        List<OfficeDTO> officeDTOList = officeService.findAll();

        return ResponseEntity.
                status(HttpStatus.OK).
                body(officeDTOList);
    }

    @GetMapping("fetch")
    public ResponseEntity<OfficeDTO> findByEpisodeNumber(@RequestParam
                                                             @Positive(message = "Episode Number cannot be negative or zero") int episodeNumber){

        OfficeDTO officeDTO = officeService.findByEpisodeNumber(episodeNumber);

        return new ResponseEntity<>(officeDTO,HttpStatus.OK);

    }

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createEpisode(@Valid @RequestBody OfficeDTO officeDTO){

        officeService.createEpisode(officeDTO);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(new ResponseDTO(OfficeConstants.STATUS_201,OfficeConstants.MESSAGE_201));
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateEpisode(@Valid @RequestBody OfficeDTO officeDTO){

        boolean updateOffice = officeService.updateOffice(officeDTO);
        if(updateOffice) {

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(OfficeConstants.STATUS_200,OfficeConstants.MESSAGE_200));

        }
        else {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new ResponseDTO(OfficeConstants.STATUS_417,OfficeConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteEpisode(@RequestParam
                                                         @Positive(message = "Episode Number cannot be negative or zero") int episodeNumber){

        boolean updateOffice = officeService.deleteEpisode(episodeNumber);
        if(updateOffice) {

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(OfficeConstants.STATUS_200,OfficeConstants.MESSAGE_200));

        }
        else {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new ResponseDTO(OfficeConstants.STATUS_417,OfficeConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("buildInfo")
    public ResponseEntity<String> buildInfo(){

        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);

    }

    @GetMapping("javaVersion")
    public ResponseEntity<String> javaVersion(){

        return ResponseEntity.
                status(HttpStatus.OK).
                body(environment.getProperty("JAVA_HOME"));

    }

    @GetMapping("contactInfo")
    public ResponseEntity<OfficeContactDetailsDTO> contactInfo(){

        return ResponseEntity.
                status(HttpStatus.OK).
                body(officeContactDetailsDTO);

    }
}
