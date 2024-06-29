package com.abhi.series.Service;

import com.abhi.series.DTO.SeriesDTO;

import java.util.List;

public interface SeriesService {

    void createSeries(SeriesDTO seriesDTO);

    boolean updateSeries(SeriesDTO seriesDTO);

    boolean deleteSeries(String uniqueId);

    SeriesDTO getSeries(String uniqueId);

    List<SeriesDTO> getAllSeries();


}
