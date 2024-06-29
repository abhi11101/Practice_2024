package com.abhi.friends.Controller;

import com.abhi.friends.Constants.FriendsConstants;
import com.abhi.friends.DTO.FriendsContactDetailsDTO;
import com.abhi.friends.DTO.FriendsDTO;
import com.abhi.friends.DTO.ResponseDTO;
import com.abhi.friends.Services.FriendsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api/friends/",produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendsController {

    private final FriendsService friendsService;
    private final Environment environment;
    private final FriendsContactDetailsDTO friendsContactDetailsDTO;

    @Value("${build.version}")
    private String buildVersion;

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createEpisode(@RequestBody @Valid FriendsDTO friendsDTO) {

        friendsService.createEpisode(friendsDTO);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(new ResponseDTO(FriendsConstants.STATUS_201,FriendsConstants.MESSAGE_201));
    }

    @GetMapping("fetch")
    public ResponseEntity<FriendsDTO> fetchEpisode(@RequestParam int episodeNumber){

        FriendsDTO friendsDTO =  friendsService.findByEpisodeNumber(episodeNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(friendsDTO);
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateEpisode(@RequestBody @Valid FriendsDTO friendsDTO){

        boolean updateFlag = friendsService.updateEpisode(friendsDTO);

        if (updateFlag){

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(FriendsConstants.STATUS_200,FriendsConstants.MESSAGE_200));

        }
        else {

            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new ResponseDTO(FriendsConstants.STATUS_417,FriendsConstants.MESSAGE_417_UPDATE));

        }
    }


    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteEpisode(@RequestParam int episodeNumber){

        boolean deleteFlag = friendsService.deleteEpisode(episodeNumber);

        if (deleteFlag){

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body(new ResponseDTO(FriendsConstants.STATUS_200,FriendsConstants.MESSAGE_200));

        }
        else {

            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(new ResponseDTO(FriendsConstants.STATUS_417,FriendsConstants.MESSAGE_417_DELETE));

        }

    }

    @GetMapping("findAll")
    public ResponseEntity<List<FriendsDTO>> findALl(){

        return ResponseEntity.status(HttpStatus.OK).body(friendsService.findAllEpisodes());
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

    @GetMapping("contactDetails")
    public ResponseEntity<FriendsContactDetailsDTO> contactDetails(){

        return ResponseEntity.
                status(HttpStatus.OK).
                body(friendsContactDetailsDTO);
    }


}
