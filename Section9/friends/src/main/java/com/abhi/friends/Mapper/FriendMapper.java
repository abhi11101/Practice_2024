package com.abhi.friends.Mapper;

import com.abhi.friends.DTOs.FriendsDTO;
import com.abhi.friends.Entity.Friends;

public class FriendMapper {

    public static Friends convertToFriends(FriendsDTO friendsDTO, Friends Friends) {

        Friends.setEpisodeNumber(friendsDTO.getEpisodeNumber());
        Friends.setEpisodeName(friendsDTO.getEpisodeName());
        Friends.setDescription(friendsDTO.getDescription());
        Friends.setSeason(friendsDTO.getSeason());
        Friends.setRating(friendsDTO.getRating());

        return Friends;
    }

    public static FriendsDTO convertToFriendsDTO(Friends Friends, FriendsDTO friendsDTO) {

        friendsDTO.setEpisodeNumber(Friends.getEpisodeNumber());
        friendsDTO.setEpisodeName(Friends.getEpisodeName());
        friendsDTO.setDescription(Friends.getDescription());
        friendsDTO.setSeason(Friends.getSeason());
        friendsDTO.setRating(Friends.getRating());

        return friendsDTO;
    }

}
