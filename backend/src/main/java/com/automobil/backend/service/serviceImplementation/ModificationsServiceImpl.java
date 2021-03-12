package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.CompleteSetMapper;
import com.automobil.backend.mapStruct.EnginMapper;
import com.automobil.backend.mapStruct.ModificationMapper;
import com.automobil.backend.mapStruct.TransmissionMapper;
import com.automobil.backend.models.Modifications;
import com.automobil.backend.models.Possibles;
import com.automobil.backend.repository.ModificationsRepository;
import com.automobil.backend.service.ModificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModificationsServiceImpl implements ModificationsService {
    private final ModificationsRepository modificationsRepository;
    private final ModificationMapper modificationMapper;
    private final EnginMapper enginMapper;
    private final CompleteSetMapper completeSetMapper;
    private final TransmissionMapper transmissionMapper;

    @Autowired
    public ModificationsServiceImpl(ModificationsRepository modificationsRepository, ModificationMapper modificationMapper, EnginMapper enginMapper, CompleteSetMapper completeSetMapper, TransmissionMapper transmissionMapper) {
        this.modificationsRepository = modificationsRepository;
        this.modificationMapper = modificationMapper;
        this.enginMapper = enginMapper;
        this.completeSetMapper = completeSetMapper;
        this.transmissionMapper = transmissionMapper;
    }

    @Override
    public List<ModificationsDto> listAll() {
        return modificationMapper.toModificationDTOs(modificationsRepository.findAll());
    }

    @Override
    public void save(ModificationsDto modificationsDto) {
        modificationsRepository.save(modificationMapper.toModification(modificationsDto));
    }

    @Override
    public void deleteById(Long id) {
        modificationsRepository.deleteById(id);
    }

    @Override
    public ModificationsDto getById(Long id) throws EntityNotFoundException {
        return modificationMapper.toModificationDTO(modificationsRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Modification")));
    }

    @Override
    public EnginesDto getEngin(Long id) throws EntityNotFoundException {
        Modifications modification = modificationsRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Modification"));
        return enginMapper.toEnginesDTO(modification.getEngines());
    }

    @Override
    public TransmissionsDto getTransmission(Long id) throws EntityNotFoundException {

        Modifications modification = modificationsRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Modification"));
        return transmissionMapper.toTransmissionDTO(modification.getTransmissions());
    }

    @Override
    public List<CompleteSetsDto> getListCompl(Long id) throws EntityNotFoundException {

        Modifications modification = modificationsRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Modification"));
        return completeSetMapper.toCompleteSetsDTOs(modification.getPossibles().stream().map(Possibles::getCompletesets).collect(Collectors.toList()));
    }


}
