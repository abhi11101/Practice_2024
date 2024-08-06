package com.abhi.friends.Respository;

import com.abhi.friends.Entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendsRepo extends JpaRepository<Friends, Long> {

    Optional<Friends> findByEpisodeNumber(int episodeNumber);

}
