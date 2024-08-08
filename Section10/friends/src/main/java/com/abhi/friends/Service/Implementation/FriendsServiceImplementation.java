package com.abhi.friends.Service.Implementation;

import com.abhi.friends.DTOs.FriendsDTO;
import com.abhi.friends.Entity.Friends;
import com.abhi.friends.ExceptionHandling.EpisodeAlreadyExist;
import com.abhi.friends.ExceptionHandling.ResourceNotFoundException;
import com.abhi.friends.Mapper.FriendMapper;
import com.abhi.friends.Respository.FriendsRepo;
import com.abhi.friends.Service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendsServiceImplementation implements FriendsService {

    private FriendsRepo friendsRepo;

    @Autowired
    public FriendsServiceImplementation(FriendsRepo friendsRepo) {
        this.friendsRepo = friendsRepo;
    }

    @Override
    public void createFriend(FriendsDTO friendsDTO) {

        Optional<Friends> friendsOptional = friendsRepo.findByEpisodeNumber(friendsDTO.getEpisodeNumber());

        if (friendsOptional.isPresent()) {
            throw new EpisodeAlreadyExist(friendsDTO.getEpisodeNumber().toString());
        }

        Friends Friends = FriendMapper.convertToFriends(friendsDTO, new Friends());

        friendsRepo.save(Friends);

    }

    @Override
    public boolean updateFriend(FriendsDTO friendsDTO) {

        Friends Friends = friendsRepo.findByEpisodeNumber(friendsDTO.getEpisodeNumber()).orElseThrow(

                () -> new ResourceNotFoundException("Episode","number",friendsDTO.getEpisodeNumber().toString())
        );

        Friends updatedFriends = FriendMapper.convertToFriends(friendsDTO, Friends);
        friendsRepo.save(updatedFriends);

        return true;
    }

    @Override
    public boolean deleteFriend(int episodeNumber) {

        Friends Friends = friendsRepo.findByEpisodeNumber(episodeNumber).orElseThrow(

                ()-> new ResourceNotFoundException("Episode", "number", ""+episodeNumber)
        );

        friendsRepo.delete(Friends);

        return true;
    }

    @Override
    public FriendsDTO findFriend(int episodeNumber) {

        Friends Friends = friendsRepo.findByEpisodeNumber(episodeNumber).orElseThrow(

                () -> new ResourceNotFoundException("Episode","number", ""+episodeNumber)
        );

        FriendsDTO friendsDTO = FriendMapper.convertToFriendsDTO(Friends,new FriendsDTO());

        return friendsDTO;
    }

    @Override
    public List<FriendsDTO> findAllFriends() {

        List<Friends> friendsList = friendsRepo.findAll();

        List<FriendsDTO> friendsDTOList = friendsList.stream()
                .map( (Friends) -> FriendMapper.convertToFriendsDTO(Friends,new FriendsDTO()))
                .collect(Collectors.toList());

        return friendsDTOList;
    }
}
