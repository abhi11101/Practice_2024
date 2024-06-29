package com.abhi.office.Services.Implementations;

import com.abhi.office.DTO.OfficeDTO;
import com.abhi.office.Entity.Office;
import com.abhi.office.ExceptionHandling.EpisodeAlreadyExistsException;
import com.abhi.office.ExceptionHandling.ResourceNotFoundException;
import com.abhi.office.Mappers.OfficeMappers;
import com.abhi.office.Repositories.OfficeRepo;
import com.abhi.office.Services.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService{

    private final OfficeRepo officeRepo;


    /**
     *
     * @return
     */
    @Override
    public List<OfficeDTO> findAll() {

        List<Office> officeList = officeRepo.findAll();
        List<OfficeDTO> officeDTOList = new ArrayList<>();

        officeList.forEach(office -> {
            OfficeDTO officeDTO = OfficeMappers.convertToOfficeDTO(office,new OfficeDTO());
            officeDTOList.add(officeDTO);
        });

        return officeDTOList;
    }

    /**
     *
     * @param episodeNumber
     * @return
     */
    @Override
    public OfficeDTO findByEpisodeNumber(Integer episodeNumber) {

        Office office = officeRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                () ->  new ResourceNotFoundException("Episode ","episode Number",episodeNumber.toString())
        );

        OfficeDTO officeDTO = OfficeMappers.convertToOfficeDTO(office,new OfficeDTO());
        return officeDTO;
    }

    /**
     *
     * @param officeDTO
     */
    @Override
    public void createEpisode(OfficeDTO officeDTO) {

        Office office = OfficeMappers.convertToOffice(officeDTO,new Office());

        Optional<Office> officeOptional = officeRepo.findByEpisodeNumber(officeDTO.getEpisodeNumber());
        if(officeOptional.isPresent()){
            throw new EpisodeAlreadyExistsException("episode already exists" + officeDTO.getEpisodeNumber());
        }
        else {
            officeRepo.save(office);
        }

    }

    /**
     *
     * @param officeDTO
     * @return
     */
    @Override
    public boolean updateOffice(OfficeDTO officeDTO) {

        Office office = officeRepo.findByEpisodeNumber(officeDTO.getEpisodeNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Episode not present","episode Number",officeDTO.getEpisodeNumber().toString())
        );

        Office savedOffice = OfficeMappers.convertToOffice(officeDTO,office);
        officeRepo.save(savedOffice);
        return true;
    }

    @Override
    public boolean deleteEpisode(Integer episodeNumber) {

        Office office = officeRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Episode not present","episode Number",episodeNumber.toString())
        );

        officeRepo.deleteById(office.getEpisodeId());
        return true;
    }
}
















