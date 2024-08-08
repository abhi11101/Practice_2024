package com.abhi.series.Service.Implementation;

import com.abhi.series.DTO.OfficeDTO;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesDetailsServiceImpl implements SeriesDetailsService<Object> {


    private final SeriesRepo seriesRepo;
    private final OfficeFeignClient officeFeignClient;
    private final FriendsFeignClient friendsFeignClient;
    private final GotFeignClient gotFeignClient;

    @Autowired
    public SeriesDetailsServiceImpl(@Qualifier("friendsClient") FriendsFeignClient friendsFeignClient,
                                    @Qualifier("officeClient") OfficeFeignClient officeFeignClient,
                                    @Qualifier("gotClient") GotFeignClient gotFeignClient, SeriesRepo seriesRepo) {

        this.friendsFeignClient = friendsFeignClient;
        this.officeFeignClient = officeFeignClient;
        this.gotFeignClient = gotFeignClient;
        this.seriesRepo = seriesRepo;
    }

    @Override
    public Object getAllEpisodes(String uniqueId) {

        Series series = seriesRepo.findByUniqueId(uniqueId).orElseThrow(
                ()-> new ResourceNotFoundException("series", "uniqueId", uniqueId)
        );

        SeriesDTO seriesDTO = SeriesMapper.convertToSeriesDTO(series, new SeriesDTO());
        SeriesDetailsDTO seriesDetailsDTO = SeriesMapper.convertToSeriesDetailsDTO(series, new SeriesDetailsDTO());

        String seriesName = series.getName();

        Object object = switch(seriesName){

            case "The Office (US)" -> officeAllEpisodes(seriesDetailsDTO);
            case "Game of Thrones" -> gotSeries(seriesDetailsDTO);
            case "Friends" -> friendsSeries(seriesDetailsDTO);
            default -> seriesDTO;
        };

        return object;
    }

    private SeriesDetailsDTO officeAllEpisodes(SeriesDetailsDTO seriesDetailsDTO) {

        if (officeFeignClient.findAllEpisodes() != null){

            List<OfficeDTO> officeDTOS = officeFeignClient.findAllEpisodes().getBody();
            seriesDetailsDTO.setEpisodeList(officeDTOS);
        }

        return seriesDetailsDTO;
    }

    private SeriesDetailsDTO friendsSeries(SeriesDetailsDTO seriesDetailsDTO){

        if (friendsFeignClient.findAllEpisodes() != null){
            seriesDetailsDTO.setEpisodeList(friendsFeignClient.findAllEpisodes().getBody());
        }

        return seriesDetailsDTO;
    }

    private SeriesDetailsDTO gotSeries(SeriesDetailsDTO seriesDetailsDTO){

        if (gotFeignClient.fetchAllEpisodes()!=null){

            seriesDetailsDTO.setEpisodeList(gotFeignClient.fetchAllEpisodes().getBody());
        }
        return seriesDetailsDTO;
    }

}
