package com.automobil.backend.service;

import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Marks;
import com.automobil.backend.models.Models;

import java.util.List;

public interface ModelsService {
    List<ModelDto> listAll();

    void save(ModelDto modelDto) throws EntityNotFoundException;

    void deleteById(Long id);

    ModelDto getById(Long id) throws EntityNotFoundException;

    Models getModelByTitle(String title) throws EntityNotFoundException;

    List<GenerationsDto> getListGen(Long Id) throws EntityNotFoundException;

    ModelDto toModelDTO(Models model);
}

