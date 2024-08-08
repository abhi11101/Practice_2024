package com.abhi.office.Mapper;

import com.abhi.office.DTO.OfficeDTO;
import com.abhi.office.Entity.Office;

public class OfficeMapper {

    public static Office convertToOffice(OfficeDTO officeDTO , Office office) {

        office.setEpisodeNumber(officeDTO.getEpisodeNumber());
        office.setEpisodeName(officeDTO.getEpisodeName());
        office.setDescription(officeDTO.getDescription());
        office.setSeason(officeDTO.getSeason());
        office.setRating(officeDTO.getRating());

        return office;
    }

    public static OfficeDTO convertToOfficeDTO(Office office, OfficeDTO officeDTO) {

        officeDTO.setEpisodeNumber(office.getEpisodeNumber());
        officeDTO.setEpisodeName(office.getEpisodeName());
        officeDTO.setDescription(office.getDescription());
        officeDTO.setSeason(office.getSeason());
        officeDTO.setRating(office.getRating());

        return officeDTO;

    }

}
