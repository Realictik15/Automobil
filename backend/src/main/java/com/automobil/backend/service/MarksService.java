package com.automobil.backend.service;

import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.models.Marks;

import java.util.List;

public interface MarksService {
    List<MarksDto> listAll();

    void save(MarksDto marksDto);

    void deleteById(Long id);

    MarksDto getById(Long id);

    Marks getMarkByTitle(String title);

    void delete(MarksDto marksDto);
}
