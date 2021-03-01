package com.automobil.backend.service;

import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.models.Marks;
import com.automobil.backend.models.Models;

import java.util.List;

public interface ModelsService {
    List<ModelDto> listAll();

    void save(ModelDto modelDto);

    void deleteById(Long id);

    ModelDto getById(Long id);

    Models getMarkByTitle(String title);

    void delete(ModelDto modelDto);
}

