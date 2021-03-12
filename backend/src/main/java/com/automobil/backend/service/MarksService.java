package com.automobil.backend.service;

import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Marks;
import com.automobil.backend.models.Models;

import java.util.List;

public interface MarksService {
    List<MarksDto> listAll();

    void save(MarksDto marksDto) throws EntityNotFoundException;

    void deleteById(Long id);

    MarksDto getById(Long id) throws EntityNotFoundException;

    Marks getMarkByTitle(String title) throws EntityNotFoundException;

    MarksDto toMarksDTO(Marks marks);

    List<ModelDto> getListModels(Long id) throws EntityNotFoundException;
}
