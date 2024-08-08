package com.abhi.friends.Service;

import com.abhi.friends.DTOs.FriendsDTO;

import java.util.List;

public interface FriendsService {

    void createFriend(FriendsDTO friendsDTO);

    boolean updateFriend(FriendsDTO friendsDTO);

    boolean deleteFriend(int episodeNumber);

    FriendsDTO findFriend(int episodeNumber);

    List<FriendsDTO> findAllFriends();

}
