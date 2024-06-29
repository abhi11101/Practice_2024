package com.abhi.series.Service.FeignClient;

import com.abhi.series.DTO.GameOfThronesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("got")
public interface GotFeignClient {

    @GetMapping(value = "/api/got/findAll", consumes = "application/json")
    ResponseEntity<List<GameOfThronesDTO>> fetchAllEpisodes();
}
