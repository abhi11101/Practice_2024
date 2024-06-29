package com.abhi.office.Services;

import com.abhi.office.DTO.OfficeDTO;

import java.util.List;

public interface OfficeService {

    /**
     *
     * @return List of All episodes
     */
    List<OfficeDTO> findAll();

    /**
     *
     * @param episodeNumber
     * @return Episode Details
     */
    OfficeDTO findByEpisodeNumber(Integer episodeNumber);

    /**
     *
     * @param officeDTO
     */
    void createEpisode(OfficeDTO officeDTO);

    /**
     *
     * @param officeDTO
     * @return
     */
    boolean updateOffice(OfficeDTO officeDTO);

    /**
     *
     * @param episodeNumber
     * @return
     */
    boolean deleteEpisode(Integer episodeNumber);
}
