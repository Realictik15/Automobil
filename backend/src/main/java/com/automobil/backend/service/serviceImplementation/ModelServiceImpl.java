package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.GenerationsMapper;
import com.automobil.backend.mapStruct.ModelsMapper;
import com.automobil.backend.models.Models;
import com.automobil.backend.repository.ModelsRepository;
import com.automobil.backend.service.MarksService;
import com.automobil.backend.service.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelsService {
    private final ModelsRepository modelsRepository;
    private final ModelsMapper modelsMapper;
    private final GenerationsMapper generationsMapper;
    private final MarksService marksService;

    @Autowired
    public ModelServiceImpl(ModelsRepository modelsRepository, ModelsMapper modelsMapper, GenerationsMapper generationsMapper, MarksService marksService) {
        this.modelsRepository = modelsRepository;
        this.modelsMapper = modelsMapper;
        this.generationsMapper = generationsMapper;
        this.marksService = marksService;
    }

    @Override
    public List<ModelDto> listAll() {
        return modelsMapper.toModelDTOs(modelsRepository.findAll());
    }

    @Override
    public void save(ModelDto modelDto) throws EntityNotFoundException {
        Models model =modelsMapper.toModels(modelDto);
        model.setMark(marksService.getMarkByTitle(modelDto.getMarkTitle()));
        modelsRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        modelsRepository.deleteById(id);
    }

    @Override
    public ModelDto getById(Long id) throws EntityNotFoundException{
        return modelsMapper.toModelDTO(modelsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id,"Models")));
    }

    @Override
    public Models getModelByTitle(String title) throws EntityNotFoundException {
        return modelsRepository.getModelsByTitle(title).orElseThrow(()-> new EntityNotFoundException(title,"Models"));
    }

    @Override
    public List<GenerationsDto> getListGen(Long Id) throws EntityNotFoundException {
        Models model = modelsRepository.findById(Id).orElseThrow(()->new EntityNotFoundException(Id,"Modeld"));
        return generationsMapper.toGenerationsDTOs(model.getGenerations());
    }

    @Override
    public ModelDto toModelDTO(Models model) {
        return modelsMapper.toModelDTO(model);
    }

}
