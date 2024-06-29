package com.abhi.series.Service.Implementation;

import com.abhi.series.DTO.OfficeDTO;
import com.abhi.series.DTO.SeriesDTO;
import com.abhi.series.DTO.SeriesDetailsDTO;
import com.abhi.series.Entity.Series;
import com.abhi.series.ExceptionHandling.ResourceNotFoundException;
import com.abhi.series.Mapper.SeriesMapper;
import com.abhi.series.Respositories.SeriesRepo;
import com.abhi.series.Service.FeignClient.GotFeignClient;
import com.abhi.series.Service.FeignClient.OfficeFeignClient;
import com.abhi.series.Service.FeignClient.FriendsFeignClient;
import com.abhi.series.Service.SeriesDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesDetailsServiceImplementation implements SeriesDetailsService {

    private final SeriesRepo seriesRepo;
    private final OfficeFeignClient officeFeignClient;
    private final GotFeignClient gotFeignClient;
    private final FriendsFeignClient friendsFeignClient;

    @Override
    public Object findAll(String uniqueId) {

        Series series = seriesRepo.findByUniqueId(uniqueId).orElseThrow(
                ()-> new ResourceNotFoundException("Series", "id",uniqueId)
        );
        SeriesDTO seriesDTO = SeriesMapper.convertToSeriesDTO(series, new SeriesDTO());
        SeriesDetailsDTO seriesDetailsDTO = SeriesMapper.convertToSeriesDetailsDTO(series,new SeriesDetailsDTO());
        String name = seriesDetailsDTO.getName();
        Object object = switch (name){
            case "The Office (US)" -> findAllOffice(seriesDetailsDTO);
            case "Game of Thrones" -> findAllGot(seriesDetailsDTO);
            case "Friends" -> findAllFriends(seriesDetailsDTO);
            default -> seriesDTO;
        };

        return object;
    }

    private SeriesDetailsDTO findAllOffice(SeriesDetailsDTO seriesDetailsDTO){
        seriesDetailsDTO.setEpisodeList(officeFeignClient.findAll().getBody());
        return seriesDetailsDTO;
    }

    private SeriesDetailsDTO findAllFriends(SeriesDetailsDTO seriesDetailsDTO){

        seriesDetailsDTO.setEpisodeList(friendsFeignClient.findALl().getBody());
        return seriesDetailsDTO;
    }

    private SeriesDetailsDTO findAllGot(SeriesDetailsDTO seriesDetailsDTO){

        seriesDetailsDTO.setEpisodeList(gotFeignClient.fetchAllEpisodes().getBody());
        return seriesDetailsDTO;
    }
}
