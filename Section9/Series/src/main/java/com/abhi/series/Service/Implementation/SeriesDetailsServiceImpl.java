package com.abhi.series.Service.Implementation;

import com.abhi.series.DTO.SeriesDTO;
import com.abhi.series.DTO.SeriesDetailsDTO;
import com.abhi.series.Entity.Series;
import com.abhi.series.ExceptionHandling.ResourceNotFoundException;
import com.abhi.series.Mapper.SeriesMapper;
import com.abhi.series.Repository.SeriesRepo;
import com.abhi.series.Service.FeignClients.FriendsFeignClient;
import com.abhi.series.Service.FeignClients.GotFeignClient;
import com.abhi.series.Service.FeignClients.OfficeFeignClient;
import com.abhi.series.Service.SeriesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesDetailsServiceImpl implements SeriesDetailsService<Object> {

    private final SeriesRepo seriesRepo;
    private FriendsFeignClient friendsFeignClient;
    private OfficeFeignClient officeFeignClient;
    private GotFeignClient gotFeignClient;

    @Autowired
    public SeriesDetailsServiceImpl(FriendsFeignClient friendsFeignClient,
                                    OfficeFeignClient officeFeignClient, GotFeignClient gotFeignClient, SeriesRepo seriesRepo) {

        this.friendsFeignClient = friendsFeignClient;
        this.officeFeignClient = officeFeignClient;
        this.gotFeignClient = gotFeignClient;
        this.seriesRepo = seriesRepo;
    }

    @Override
    public Object getAllEpisodes(String uniqueId) {

        Series series = seriesRepo.findByUniqueId(uniqueId).orElseThrow(
                () -> new ResourceNotFoundException("Series", "uniqueId",uniqueId)
        );

        SeriesDTO seriesDTO = SeriesMapper.convertToSeriesDTO(series,new SeriesDTO());

        SeriesDetailsDTO seriesDetailsDTO = SeriesMapper.convertToSeriesDetailsDTO(series,  new SeriesDetailsDTO());

        String name = seriesDTO.getName();

        Object object = switch (name){

            case "The Office (US)" -> officeSeries(seriesDetailsDTO);
            case "Game of Thrones" -> gotSeries(seriesDetailsDTO);
            case "Friends" -> friendsSeries(seriesDetailsDTO);
            default -> seriesDTO;

        };

        return object;
    }

    private SeriesDetailsDTO friendsSeries(SeriesDetailsDTO seriesDetailsDTO){

        seriesDetailsDTO.setEpisodeList(friendsFeignClient.findAllEpisodes().getBody());

        return seriesDetailsDTO;
    }

    private SeriesDetailsDTO officeSeries(SeriesDetailsDTO seriesDetailsDTO){

        seriesDetailsDTO.setEpisodeList(officeFeignClient.findAllEpisodes().getBody());
        return seriesDetailsDTO;
    }

    private SeriesDetailsDTO gotSeries(SeriesDetailsDTO seriesDetailsDTO){

        seriesDetailsDTO.setEpisodeList(gotFeignClient.fetchAllEpisodes().getBody());
        return seriesDetailsDTO;
    }
}
