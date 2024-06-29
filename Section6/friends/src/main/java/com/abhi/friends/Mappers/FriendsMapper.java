package com.abhi.friends.Mappers;

import com.abhi.friends.DTO.FriendsDTO;
import com.abhi.friends.Entity.Friends;

public class FriendsMapper {

    public static Friends convertToFriends(FriendsDTO friendsDTO, Friends friends){

        friends.setEpisodeNumber(friendsDTO.getEpisodeNumber());
        friends.setEpisodeName(friendsDTO.getEpisodeName());
        friends.setDescription(friendsDTO.getDescription());
        friends.setSeason(friendsDTO.getSeason());
        friends.setRating(friendsDTO.getRating());

        return friends;
    }

    public static FriendsDTO convertToFriendsDTO(Friends friends, FriendsDTO friendsDTO){

        friendsDTO.setEpisodeNumber(friends.getEpisodeNumber());
        friendsDTO.setEpisodeName(friends.getEpisodeName());
        friendsDTO.setDescription(friends.getDescription());
        friendsDTO.setSeason(friends.getSeason());
        friendsDTO.setRating(friends.getRating());

        return friendsDTO;
    }

}
