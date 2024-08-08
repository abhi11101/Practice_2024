package com.abhi.office.Service;

import com.abhi.office.DTO.OfficeDTO;

import java.util.List;

public interface OfficeService {

    void createOffice(OfficeDTO officeDTO);

    boolean updateOffice(OfficeDTO officeDTO);

    boolean deleteOffice(int episodeNumber);

    OfficeDTO fetchOffice(int episodeNumber);

    List<OfficeDTO> fetchAllOffice();
}
