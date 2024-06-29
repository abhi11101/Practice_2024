package com.abhi.series.Service.Implementation;

import com.abhi.series.DTO.SeriesDTO;
import com.abhi.series.Entity.Series;
import com.abhi.series.ExceptionHandling.ResourceNotFoundException;
import com.abhi.series.ExceptionHandling.SeriesAlreadyExistException;
import com.abhi.series.Mapper.SeriesMapper;
import com.abhi.series.Respositories.SeriesRepo;
import com.abhi.series.Service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeriesImplementation implements SeriesService {

    private final SeriesRepo seriesRepo;


    @Override
    public void createSeries(SeriesDTO seriesDTO) {

        Optional<Series> seriesOptional = seriesRepo.findByUniqueId(seriesDTO.getUniqueId());
        if (seriesOptional.isPresent()){

            throw new SeriesAlreadyExistException(seriesDTO.getUniqueId());

        }

        Series series = SeriesMapper.convertToSeries(seriesDTO,new Series());
        seriesRepo.save(series);
    }

    @Override
    public boolean updateSeries(SeriesDTO seriesDTO) {

        Series series = seriesRepo.findByUniqueId(seriesDTO.getUniqueId()).orElseThrow(

                () -> new ResourceNotFoundException("series", "id", seriesDTO.getUniqueId())
        );

        Series savedSeries = SeriesMapper.convertToSeries(seriesDTO,series);
        seriesRepo.save(savedSeries);

        return true;
    }

    @Override
    public boolean deleteSeries(String uniqueId) {

        Series series = seriesRepo.findByUniqueId(uniqueId).orElseThrow(

                () -> new ResourceNotFoundException("series", "id", uniqueId)
        );

        seriesRepo.deleteById(series.getSeriesId());

        return true;
    }

    @Override
    public SeriesDTO getSeries(String uniqueId) {

        Series series  = seriesRepo.findByUniqueId(uniqueId).orElseThrow(
                () -> new ResourceNotFoundException("series", "id", uniqueId)
        );

        SeriesDTO seriesDTO = SeriesMapper.convertToSeriesDTO(series,new SeriesDTO());

        return seriesDTO;
    }

    @Override
    public List<SeriesDTO> getAllSeries() {

        List<Series> seriesList = seriesRepo.findAll();
        List<SeriesDTO> seriesDTOList = new ArrayList<>();

        seriesList.forEach(

                (series)-> {
                    SeriesDTO seriesDTO = SeriesMapper.convertToSeriesDTO(series,new SeriesDTO());
                    seriesDTOList.add(seriesDTO);
                }
        );

        return seriesDTOList;
    }
}










