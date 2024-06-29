package com.abhi.office.Repositories;

import com.abhi.office.Entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficeRepo extends JpaRepository<Office, Long> {

    Optional<Office> findByEpisodeNumber(int episodeNumber);

}
