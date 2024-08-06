package com.abhi.office.Service.Implementation;

import com.abhi.office.DTO.OfficeDTO;
import com.abhi.office.Entity.Office;
import com.abhi.office.ExceptionHandling.EpisodeAlreadyExistException;
import com.abhi.office.ExceptionHandling.ResourceNotFoundException;
import com.abhi.office.Mappers.OfficeMappers;
import com.abhi.office.Repository.OfficeRepo;
import com.abhi.office.Service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private OfficeRepo officeRepo;

    @Autowired
    public OfficeServiceImpl(OfficeRepo officeRepo) {
        this.officeRepo = officeRepo;
    }

    @Override
    public void createOffice(OfficeDTO officeDTO) {

        Optional<Office> officeOptional = officeRepo.findByEpisodeNumber(officeDTO.getEpisodeNumber());

        if (officeOptional.isPresent()) {
            throw new EpisodeAlreadyExistException(officeDTO.getEpisodeNumber()+"");
        }

        Office office = OfficeMappers.convertToOffice(officeDTO, new Office());
        officeRepo.save(office);

    }

    @Override
    public boolean updateOffice(OfficeDTO officeDTO) {

        Office office = officeRepo.findByEpisodeNumber(officeDTO.getEpisodeNumber()).orElseThrow(

                () ->  new ResourceNotFoundException("episode", "number", officeDTO.getEpisodeNumber()+"")
        );

        Office updatedOffice = OfficeMappers.convertToOffice(officeDTO, office);
        officeRepo.save(updatedOffice);
        return true;
    }

    @Override
    public boolean deleteOffice(int episodeNumber) {

        Office office = officeRepo.findByEpisodeNumber(episodeNumber).orElseThrow(

                () ->  new ResourceNotFoundException("episode", "number", episodeNumber+"")
        );

        officeRepo.delete(office);

        return true;
    }

    @Override
    public OfficeDTO getOffice(int episodeNumber) {

        Office office = officeRepo.findByEpisodeNumber(episodeNumber).orElseThrow(

                () -> new ResourceNotFoundException("episode", "number", episodeNumber+"")
        );

        OfficeDTO officeDTO = OfficeMappers.convertToOfficeDTO(office, new OfficeDTO());

        return officeDTO;
    }

    @Override
    public List<OfficeDTO> getAllOffice() {

        List<Office> offices = officeRepo.findAll();

        return  offices.stream()
                .map(
                        (office) -> OfficeMappers.convertToOfficeDTO(office, new OfficeDTO())
                )
                .collect(Collectors.toList());

    }
}
