package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.TransmissionMapper;
import com.automobil.backend.models.Transmissions;
import com.automobil.backend.repository.GearboxesRepository;
import com.automobil.backend.repository.TransmissionsRepository;
import com.automobil.backend.service.GearBoxesService;
import com.automobil.backend.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionServiceImpl implements TransmissionService {
    private final TransmissionMapper transmissionMapper;
    private final TransmissionsRepository transmissionsRepository;
    private final GearboxesRepository gearboxesRepository;

    @Autowired
    TransmissionServiceImpl(TransmissionMapper transmissionMapper, TransmissionsRepository transmissionsRepository,
                            GearBoxesService gearBoxesService, GearboxesRepository gearboxesRepository) {
        this.transmissionMapper = transmissionMapper;
        this.transmissionsRepository = transmissionsRepository;
        this.gearboxesRepository = gearboxesRepository;
    }

    @Override
    public List<TransmissionsDto> listAll() {
        return transmissionMapper.toTransmissionDTOs(transmissionsRepository.findAll());
    }

    @Override
    public void save(TransmissionsDto transmissionsDto) throws EntityNotFoundException {
        Transmissions transmissions =transmissionMapper.toTransmissions(transmissionsDto);
        transmissions.setGearbox(gearboxesRepository.findById(transmissionsDto.getGearBoxDto().getIdGear()).
            orElseThrow(()->new EntityNotFoundException(transmissionsDto.getGearBoxDto().getIdGear(),"GearBox")));
        transmissionsRepository.save(transmissions);
    }

    @Override
    public void deleteById(Long id) {

        transmissionsRepository.deleteById(id);
    }

    @Override
    public TransmissionsDto getById(Long id) throws EntityNotFoundException {
        return transmissionMapper.toTransmissionDTO(transmissionsRepository.findById(id).
            orElseThrow(()->new EntityNotFoundException(id,"Transmission")));
    }

}
