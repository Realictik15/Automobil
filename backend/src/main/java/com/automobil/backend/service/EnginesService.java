package com.automobil.backend.service;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.exeption.EntityNotFoundException;

import java.util.List;

public interface EnginesService {
    List<EnginesDto> listAll();

    EnginesDto getById(Long id) throws EntityNotFoundException;

    void save(EnginesDto enginesDto);


}
