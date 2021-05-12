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
import com.automobil.backend.models.Generations;
import com.automobil.backend.models.Modifications;
import com.automobil.backend.models.Possibles;
import com.automobil.backend.repository.*;
import com.automobil.backend.service.ModificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModificationsServiceImpl implements ModificationsService {
    private final ModificationsRepository modificationsRepository;
    private final ModificationMapper modificationMapper;
    private final EnginMapper enginMapper;
    private final CompleteSetMapper completeSetMapper;
    private final CompletesetsRepository completesetsRepository;
    private final TransmissionMapper transmissionMapper;
    private final GenerationsRepository generationsRepository;
    private final EnginesRepository enginesRepository;
    private final TransmissionsRepository transmissionsRepository;

    @Autowired
    public ModificationsServiceImpl(ModificationsRepository modificationsRepository, ModificationMapper modificationMapper,
                                    EnginMapper enginMapper, CompleteSetMapper completeSetMapper,
                                    CompletesetsRepository completesetsRepository, TransmissionMapper transmissionMapper, GenerationsRepository generationsRepository,
                                    EnginesRepository enginesRepository, TransmissionsRepository transmissionsRepository) {
        this.modificationsRepository = modificationsRepository;
        this.modificationMapper = modificationMapper;
        this.enginMapper = enginMapper;
        this.completeSetMapper = completeSetMapper;
        this.completesetsRepository = completesetsRepository;
        this.transmissionMapper = transmissionMapper;
        this.generationsRepository = generationsRepository;
        this.enginesRepository = enginesRepository;
        this.transmissionsRepository = transmissionsRepository;
    }

    @Override
    public List<ModificationsDto> listAll() {
        return modificationMapper.toModificationDTOs(modificationsRepository.findAll());
    }

    @Override
    public void save(ModificationsDto modificationsDto) throws EntityNotFoundException {
        Modifications modifications = modificationMapper.toModification(modificationsDto);

        Generations generation = generationsRepository.findById(modificationsDto.getGenerationsDto().getIdGen()).
            orElseThrow(() -> new EntityNotFoundException(modificationsDto.getGenerationsDto().getIdGen(), "Generation"));
        modifications.setGenerations(generation);
        modifications.setEngines(enginesRepository.findById(modificationsDto.getEnginesDto().getIdeng()).
            orElseThrow(() -> new EntityNotFoundException(modificationsDto.getEnginesDto().getIdeng(), "Engin")));
        modifications.setTransmissions(transmissionsRepository.findById(modificationsDto.getTransmissionsDto().getIdTrans()).
            orElseThrow(() -> new EntityNotFoundException(modificationsDto.getTransmissionsDto().getIdTrans(), "Transmission")));

        generation.setModifications(new ArrayList<Modifications>() {{
            addAll(generation.getModifications());
            add(modifications);
        }});
        if(!modificationsDto.getIdComplSet().isEmpty()){
          modificationsDto.getIdComplSet().forEach(x-> {
              try {
                 modifications.setPossibles(Collections.singletonList(new Possibles(null, modifications, completesetsRepository.findById(x).orElseThrow(EntityNotFoundException::new))));
              } catch (EntityNotFoundException e) {
                  e.printStackTrace();
              }
          });
        }
        modificationsRepository.save(modifications);
    }

    @Override
    public ModificationsDto getById(Long id) throws EntityNotFoundException {
        return modificationMapper.toModificationDTO(modificationsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Modification")));
    }

    @Override
    public EnginesDto getEngin(Long id) throws EntityNotFoundException {
        Modifications modification = modificationsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Modification"));
        return enginMapper.toEnginesDTO(modification.getEngines());
    }

    @Override
    public TransmissionsDto getTransmission(Long id) throws EntityNotFoundException {

        Modifications modification = modificationsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Modification"));
        return transmissionMapper.toTransmissionDTO(modification.getTransmissions());
    }

    @Override
    public List<CompleteSetsDto> getListCompl(Long id) throws EntityNotFoundException {

        Modifications modification = modificationsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Modification"));
        return completeSetMapper.toCompleteSetsDTOs(modification.getPossibles().stream().
            map(Possibles::getCompletesets).collect(Collectors.toList()));
    }


}
