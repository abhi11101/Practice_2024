package com.abhi.series.Service.FeignClients;

import com.abhi.series.DTO.OfficeDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "office",fallback = OfficeFallback.class)
@Qualifier("officeClient")
public interface OfficeFeignClient {

    @GetMapping(value = "/api/office/findAll",consumes = "application/json")
    ResponseEntity<List<OfficeDTO>> findAllEpisodes();
}
