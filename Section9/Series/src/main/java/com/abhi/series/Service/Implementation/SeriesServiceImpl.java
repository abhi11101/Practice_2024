package com.abhi.series.Service.Implementation;

import com.abhi.series.DTO.SeriesDTO;
import com.abhi.series.Entity.Series;
import com.abhi.series.ExceptionHandling.EpisodeAlreadyExistException;
import com.abhi.series.ExceptionHandling.ResourceNotFoundException;
import com.abhi.series.Mapper.SeriesMapper;
import com.abhi.series.Repository.SeriesRepo;
import com.abhi.series.Service.SeriesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesServiceImpl implements SeriesService {

    private SeriesRepo seriesRepo;

    public SeriesServiceImpl(SeriesRepo seriesRepo) {
        this.seriesRepo = seriesRepo;
    }

    @Override
    public void createSeries(SeriesDTO seriesDTO) {

        Optional<Series> seriesOptional = seriesRepo.findByUniqueId(seriesDTO.getUniqueId());

        if (seriesOptional.isPresent()) {
            throw new EpisodeAlreadyExistException("Episode already exist" + seriesDTO.getUniqueId());
        }

        Series series = SeriesMapper.convertToSeries(seriesDTO, new Series());

        seriesRepo.save(series);
    }

    @Override
    public boolean updateSeries(SeriesDTO seriesDTO) {

        Series series = seriesRepo.findByUniqueId(seriesDTO.getUniqueId()).orElseThrow(
                () -> new ResourceNotFoundException("Episode", "uniqueId", seriesDTO.getUniqueId())
        );

        Series updatedSeries = SeriesMapper.convertToSeries(seriesDTO, series);
        seriesRepo.save(updatedSeries);

        return true;
    }

    @Override
    public boolean deleteSeries(String uniqueId) {

        Series series = seriesRepo.findByUniqueId(uniqueId).orElseThrow(
                () -> new ResourceNotFoundException("Episode", "uniqueId", uniqueId)
        );

        seriesRepo.delete(series);

        return true;
    }

    @Override
    public SeriesDTO getSeries(String uniqueId) {

        Series series = seriesRepo.findByUniqueId(uniqueId).orElseThrow(
                () -> new ResourceNotFoundException("Episode", "uniqueId", uniqueId)
        );

        SeriesDTO seriesDTO = SeriesMapper.convertToSeriesDTO(series, new SeriesDTO());

        return seriesDTO;
    }

    @Override
    public List<SeriesDTO> getAllSeries() {

        List<Series> seriesList = seriesRepo.findAll();

        return seriesList.stream()
                .map(
                        (series) -> SeriesMapper.convertToSeriesDTO(series, new SeriesDTO())
                )
                .toList();

    }
}
