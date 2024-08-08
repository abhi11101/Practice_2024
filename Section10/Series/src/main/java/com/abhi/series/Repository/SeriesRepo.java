package com.abhi.series.Repository;

import com.abhi.series.Entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeriesRepo extends JpaRepository<Series, Long> {

    Optional<Series> findByUniqueId(String uniqueId);
}
