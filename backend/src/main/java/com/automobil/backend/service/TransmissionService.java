package com.automobil.backend.service;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.TransmissionsDto;

import java.util.List;

public interface TransmissionService {
    List<TransmissionsDto> listAll();

    void save(TransmissionsDto transmissionsDto);

    void deleteById(Long id);

    TransmissionsDto getById(Long id);

    void delete(TransmissionsDto transmissionsDto);

}
