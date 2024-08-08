package com.abhi.series.Service.FeignClients;

import com.abhi.series.DTO.OfficeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "officeFallback")
public class OfficeFallback implements OfficeFeignClient{

    @Override
    public ResponseEntity<List<OfficeDTO>> findAllEpisodes() {
        return null;
    }
}
