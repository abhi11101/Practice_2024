package com.abhi.friends.Services.Implementation;

import com.abhi.friends.DTO.FriendsDTO;
import com.abhi.friends.Entity.Friends;
import com.abhi.friends.ExceptionHandling.EpisodeAlreadyExistsException;
import com.abhi.friends.ExceptionHandling.ResourceNotFoundException;
import com.abhi.friends.Mappers.FriendsMapper;
import com.abhi.friends.Repositories.FriendsRepo;
import com.abhi.friends.Services.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendsService {

    private final FriendsRepo friendsRepo;

    @Override
    public void createEpisode(FriendsDTO friendsDTO) {

        Optional<Friends> friendsOptional = friendsRepo.findByEpisodeNumber(friendsDTO.getEpisodeNumber());

        if (friendsOptional.isPresent()){
            throw new EpisodeAlreadyExistsException(""+ friendsDTO.getEpisodeNumber());
        }

        Friends friends = FriendsMapper.convertToFriends(friendsDTO,new Friends());
        friendsRepo.save(friends);

    }

    @Override
    public boolean updateEpisode(FriendsDTO friendsDTO) {

        Friends friends = friendsRepo.findByEpisodeNumber(friendsDTO.getEpisodeNumber()).orElseThrow(

                ()-> new ResourceNotFoundException("Episode"," number", ""+friendsDTO.getEpisodeNumber())

        );

        Friends savedFriend = FriendsMapper.convertToFriends(friendsDTO, friends);
        friendsRepo.save(savedFriend);

        return true;
    }

    @Override
    public boolean deleteEpisode(int episodeNumber) {

        Friends friends = friendsRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                () -> new ResourceNotFoundException("Episode"," number", ""+episodeNumber)
        );

        friendsRepo.deleteById(friends.getFriendsId());

        return true;
    }

    @Override
    public FriendsDTO findByEpisodeNumber(int episodeNumber) {

        Friends friends = friendsRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Episode"," number", ""+episodeNumber)

        );

        FriendsDTO friendsDTO = FriendsMapper.convertToFriendsDTO(friends,new FriendsDTO());
        return friendsDTO;
    }

    @Override
    public List<FriendsDTO> findAllEpisodes() {

        List<Friends> friendsList  = friendsRepo.findAll();

        List<FriendsDTO> friendsDTOList = new ArrayList<>();
        friendsList.forEach(

                (friend) -> {

                    FriendsDTO friendsDTO = FriendsMapper.convertToFriendsDTO(friend,new FriendsDTO());
                    friendsDTOList.add(friendsDTO);
                }
        );

        return friendsDTOList;
    }
}
