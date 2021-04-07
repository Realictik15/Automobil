package com.automobil.backend.service;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.exeption.EntityNotFoundException;

import java.util.List;

public interface TransmissionService {
    List<TransmissionsDto> listAll();

    void save(TransmissionsDto transmissionsDto) throws EntityNotFoundException;

    void deleteById(Long id);

    TransmissionsDto getById(Long id) throws EntityNotFoundException;
}
