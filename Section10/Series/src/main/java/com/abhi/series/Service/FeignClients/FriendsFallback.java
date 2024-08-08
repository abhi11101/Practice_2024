package com.abhi.series.Service.FeignClients;

import com.abhi.series.DTO.FriendsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "friendsFallback")
public class FriendsFallback implements FriendsFeignClient  {

    @Override
    public ResponseEntity<List<FriendsDTO>> findAllEpisodes() {
        return null;
    }
}
