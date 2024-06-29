package com.abhi.series.Service.FeignClient;

import com.abhi.series.DTO.FriendsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "friends")
public interface FriendsFeignClient {

    @GetMapping(value = "/api/friends/findAll", consumes = "application/json")
    ResponseEntity<List<FriendsDTO>> findALl();
}
