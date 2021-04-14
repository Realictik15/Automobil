package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.GearBoxesDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.GearBoxesMapper;
import com.automobil.backend.models.GearBoxes;
import com.automobil.backend.repository.GearboxesRepository;
import com.automobil.backend.service.GearBoxesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GearBoxesServiceImpl implements GearBoxesService {
    private final GearboxesRepository gearboxesRepository;
    private final GearBoxesMapper gearBoxesMapper;

    @Autowired
    public GearBoxesServiceImpl(GearboxesRepository gearboxesRepository, GearBoxesMapper gearBoxesMapper) {
        this.gearboxesRepository = gearboxesRepository;
        this.gearBoxesMapper = gearBoxesMapper;
    }

    @Override
    public GearBoxesDto getById(Long id) throws EntityNotFoundException {
        return gearBoxesMapper.toGearBoxesDTO(gearboxesRepository.findById(id).
            orElseThrow(()->new EntityNotFoundException(id,"Gearboxes")));
    }

    @Override
    public List<GearBoxesDto> getListGearBox() {
        return gearBoxesMapper.toGearBoxesDTOs(gearboxesRepository.findAll().stream().sorted(Comparator.comparing(GearBoxes::getTitle)).collect(Collectors.toList()));
    }

    @Override
    public GearBoxes toGearBox(GearBoxesDto gearBoxesDto) {
        return gearBoxesMapper.toGearBoxes(gearBoxesDto);
    }
}
