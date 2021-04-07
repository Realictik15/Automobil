package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.CarbodyDto;
import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.CarbodyMapper;
import com.automobil.backend.mapStruct.GenerationsMapper;
import com.automobil.backend.mapStruct.ModificationMapper;
import com.automobil.backend.mapStruct.SizessMapper;
import com.automobil.backend.models.Bodies;
import com.automobil.backend.models.Generations;
import com.automobil.backend.models.Models;
import com.automobil.backend.repository.GenerationsRepository;
import com.automobil.backend.repository.ModelsRepository;
import com.automobil.backend.service.GenerationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenetationServiceImpl implements GenerationsService {
    private final GenerationsRepository generationsRepository;
    private final GenerationsMapper generationsMapper;
    private final SizessMapper sizessMapper;
    private final ModificationMapper modificationMapper;
    private final ModelsRepository modelsRepository;
    private final CarbodyMapper carbodyMapper;

    @Autowired
    public GenetationServiceImpl(GenerationsRepository generationsRepository, GenerationsMapper generationsMapper,
                                 SizessMapper sizessMapper, ModificationMapper modificationMapper,
                                 ModelsRepository modelsRepository, CarbodyMapper carbodyMapper) {
        this.generationsRepository = generationsRepository;
        this.generationsMapper = generationsMapper;
        this.sizessMapper = sizessMapper;
        this.modificationMapper = modificationMapper;
        this.modelsRepository = modelsRepository;
        this.carbodyMapper = carbodyMapper;
    }

    @Override
    public List<GenerationsDto> listAll() {
        return generationsMapper.toGenerationsDTOs(generationsRepository.findAll());
    }


    @Override
    public void deleteById(Long id) {
        generationsRepository.deleteById(id);
    }

    @Override
    public void save(GenerationsDto generationsDto) throws EntityNotFoundException {
        Generations generation = generationsMapper.toGenerations(generationsDto);
        Models model = modelsRepository.getModelsByTitle(generationsDto.getModelTitle()).orElseThrow(EntityNotFoundException::new);
        generation.setModel(model);
        model.setGenerations(new ArrayList<Generations>() {{
            addAll(model.getGenerations());
            add(generation);
        }});
        generationsRepository.save(generation);
    }

    @Override
    public GenerationsDto getById(Long id) throws EntityNotFoundException {
        return generationsMapper.toGenerationsDTO(generationsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Generation")));
    }

    /*
    первый претендет на удаление
     */
    @Override
    public List<SizessDto> getAllSize(Long id) throws EntityNotFoundException {
        Generations gen = generationsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Generatoin"));
        return sizessMapper.toSizessDTOs(gen.getBodies().stream().map(Bodies::getSizess).
            collect(Collectors.toList()));
    }

    @Override
    public List<ModificationsDto> getModificationByGen(Long id) throws EntityNotFoundException {
        Generations gen = generationsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Generatoin"));
        return modificationMapper.toModificationDTOs(gen.getModifications());
    }

    @Override
    public List<CarbodyDto> getListCarBodyByGenenerationId(Long id) throws EntityNotFoundException {
        return carbodyMapper.toCarBodyDTOs(generationsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Generation")).
            getBodies().stream().map(Bodies::getCarbody).collect(Collectors.toList()));
    }


}
