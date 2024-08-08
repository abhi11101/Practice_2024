package com.abhi.series.Service.FeignClients;

import com.abhi.series.DTO.GameOfThronesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "gotFallback")
public class GotFallback implements GotFeignClient{

    @Override
    public ResponseEntity<List<GameOfThronesDTO>> fetchAllEpisodes() {
        return null;
    }
}
