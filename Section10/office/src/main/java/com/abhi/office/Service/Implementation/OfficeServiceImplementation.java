package com.abhi.office.Service.Implementation;

import com.abhi.office.DTO.OfficeDTO;
import com.abhi.office.Entity.Office;
import com.abhi.office.ExceptionHandling.EpisodeAlreadyExistException;
import com.abhi.office.ExceptionHandling.ResourceNotFoundException;
import com.abhi.office.Mapper.OfficeMapper;
import com.abhi.office.Repository.OfficeRepo;
import com.abhi.office.Service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeServiceImplementation implements OfficeService {

    private OfficeRepo officeRepo;

    @Autowired
    public OfficeServiceImplementation(OfficeRepo officeRepo) {
        this.officeRepo = officeRepo;
    }

    @Override
    public void createOffice(OfficeDTO officeDTO) {

        Optional<Office> officeOptional = officeRepo.findByEpisodeNumber(officeDTO.getEpisodeNumber());

        if (officeOptional.isPresent()) {

            throw new EpisodeAlreadyExistException("Episode already exist " + officeDTO.getEpisodeNumber());
        }

        Office office = OfficeMapper.convertToOffice(officeDTO, new Office());

        officeRepo.save(office);

    }

    @Override
    public boolean updateOffice(OfficeDTO officeDTO) {

        Office office = officeRepo.findByEpisodeNumber(officeDTO.getEpisodeNumber()).orElseThrow(
                ()->  new ResourceNotFoundException("Episode not found " + officeDTO.getEpisodeNumber())
        );

        Office updatedOffice = OfficeMapper.convertToOffice(officeDTO, office);
        officeRepo.save(updatedOffice);
        return true;
    }

    @Override
    public boolean deleteOffice(int episodeNumber) {

        Office office = officeRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                ()->  new ResourceNotFoundException("Episode not found " + episodeNumber)
        );

        officeRepo.delete(office);

        return true;
    }

    @Override
    public OfficeDTO fetchOffice(int episodeNumber) {

        Office office = officeRepo.findByEpisodeNumber(episodeNumber).orElseThrow(
                ()->  new ResourceNotFoundException("Episode not found " + episodeNumber)
        );

        OfficeDTO officeDTO = OfficeMapper.convertToOfficeDTO(office,new OfficeDTO());

        return officeDTO;
    }

    @Override
    public List<OfficeDTO> fetchAllOffice() {

        List<Office> offices = officeRepo.findAll();
        return offices.stream()
                .map( o -> OfficeMapper.convertToOfficeDTO(o, new OfficeDTO()))
                .toList();
    }
}
