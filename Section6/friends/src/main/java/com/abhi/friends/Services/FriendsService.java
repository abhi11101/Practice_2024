package com.abhi.friends.Services;

import com.abhi.friends.DTO.FriendsDTO;

import java.util.List;

public interface FriendsService {

    void createEpisode(FriendsDTO friendsDTO);

    boolean updateEpisode(FriendsDTO friendsDTO);

    boolean deleteEpisode(int episodeNumber);

    FriendsDTO findByEpisodeNumber(int episodeNumber);

    List<FriendsDTO> findAllEpisodes();

}
