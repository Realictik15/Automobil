package com.automobil.backend.service;

import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.CompleteSets;

import java.util.List;

public interface CompleteSetsService {

    void save(CompleteSetsDto completeSetsDto);

    void deleteById(Long id);

    CompleteSetsDto getById(Long id) throws EntityNotFoundException;

    List<CompleteSetsDto> getAll();
}
