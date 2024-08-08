package com.abhi.series.Service.FeignClients;

import com.abhi.series.DTO.GameOfThronesDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient( value = "got",fallback = GotFallback.class)
@Qualifier("gotClient")
public interface GotFeignClient {

    @GetMapping(value = "/api/got/findAll",consumes = "application/json")
    ResponseEntity<List<GameOfThronesDTO>> fetchAllEpisodes();

}
