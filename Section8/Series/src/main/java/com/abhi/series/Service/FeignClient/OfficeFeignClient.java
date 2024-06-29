package com.abhi.series.Service.FeignClient;

import com.abhi.series.DTO.OfficeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("office")
public interface OfficeFeignClient {

    @GetMapping(value = "/api/office/findAll", consumes = "application/json")
    ResponseEntity<List<OfficeDTO>> findAll();
}
