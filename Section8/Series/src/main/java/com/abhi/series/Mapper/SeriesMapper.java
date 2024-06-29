package com.abhi.series.Mapper;

import com.abhi.series.DTO.SeriesDTO;
import com.abhi.series.DTO.SeriesDetailsDTO;
import com.abhi.series.Entity.Series;

public class SeriesMapper {

    public static Series convertToSeries(SeriesDTO seriesDTO, Series series){

        series.setName(seriesDTO.getName());
        series.setDescription(seriesDTO.getDescription());
        series.setGenre(seriesDTO.getGenre());
        series.setReleaseYear(seriesDTO.getReleaseYear());
        series.setUniqueId(seriesDTO.getUniqueId());
        return series;
    }

    public static SeriesDTO convertToSeriesDTO(Series series, SeriesDTO seriesDTO){

        seriesDTO.setName(series.getName());
        seriesDTO.setDescription(series.getDescription());
        seriesDTO.setGenre(series.getGenre());
        seriesDTO.setReleaseYear(series.getReleaseYear());
        seriesDTO.setUniqueId(series.getUniqueId());

        return seriesDTO;
    }

    public static SeriesDetailsDTO convertToSeriesDetailsDTO(Series series, SeriesDetailsDTO seriesDetailsDTO){

        seriesDetailsDTO.setName(series.getName());
        seriesDetailsDTO.setDescription(series.getDescription());
        seriesDetailsDTO.setGenre(series.getGenre());
        seriesDetailsDTO.setReleaseYear(series.getReleaseYear());
        seriesDetailsDTO.setUniqueId(series.getUniqueId());

        return seriesDetailsDTO;
    }

}
