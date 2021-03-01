package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.mapStruct.MarksMapper;
import com.automobil.backend.mapStruct.ModelsMapper;
import com.automobil.backend.models.Models;
import com.automobil.backend.repository.MarksRepository;
import com.automobil.backend.repository.ModelsRepository;
import com.automobil.backend.service.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModelServiceImpl implements ModelsService {
    private final ModelsRepository modelsRepository;
    private final ModelsMapper modelsMapper;

    @Autowired
    public ModelServiceImpl(ModelsRepository modelsRepository, ModelsMapper modelsMapper) {
        this.modelsRepository = modelsRepository;
        this.modelsMapper = modelsMapper;
    }

    @Override
    public List<ModelDto> listAll() {
        return null;
    }

    @Override
    public void save(ModelDto modelDto) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public ModelDto getById(Long id) {
        return modelsMapper.toModelDTO(modelsRepository.findById(id).get());
    }

    @Override
    public Models getMarkByTitle(String title) {
        return modelsRepository.getModelsByTitle(title);
    }

    @Override
    public void delete(ModelDto modelDto) {

    }
}
