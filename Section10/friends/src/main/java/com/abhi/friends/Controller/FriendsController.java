package com.abhi.friends.Controller;

import com.abhi.friends.Constants.FriendsConstants;
import com.abhi.friends.DTOs.FriendsDTO;
import com.abhi.friends.DTOs.ResponseDTO;
import com.abhi.friends.Service.FriendsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/friends/", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class FriendsController {

    private FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createEpisode(@RequestBody @Valid FriendsDTO friendsDTO) {

        friendsService.createFriend(friendsDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(FriendsConstants.STATUS_201,FriendsConstants.MESSAGE_201));

    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO> updateEpisode(@RequestBody @Valid FriendsDTO friendsDTO) {

        boolean updateFlag = friendsService.updateFriend(friendsDTO);

        if (updateFlag){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(FriendsConstants.STATUS_200,FriendsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(FriendsConstants.STATUS_417,FriendsConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDTO> deleteEpisode(@RequestParam int episodeNumber) {

        boolean deleteFlag = friendsService.deleteFriend(episodeNumber);

        if (deleteFlag){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(FriendsConstants.STATUS_200,FriendsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(FriendsConstants.STATUS_417,FriendsConstants.MESSAGE_417_DELETE));
        }

    }

    @GetMapping("fetch")
    public ResponseEntity<FriendsDTO> getEpisode(@RequestParam int episodeNumber) {

        FriendsDTO friendsDTO = friendsService.findFriend(episodeNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(friendsDTO);

    }

    @GetMapping("findAll")
    public ResponseEntity<List<FriendsDTO>> findAllEpisodes() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(friendsService.findAllFriends());

    }


}
