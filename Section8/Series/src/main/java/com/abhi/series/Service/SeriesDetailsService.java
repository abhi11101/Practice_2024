package com.abhi.series.Service;

import com.abhi.series.DTO.SeriesDetailsDTO;

public interface SeriesDetailsService<T>{

    T findAll(String uniqueId);
}
