package com.automobil.backend.service;

import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.exeption.EntityNotFoundException;

import java.util.List;

public interface ModificationsService {
    List<ModificationsDto> listAll();

    void save(ModificationsDto modificationsDto);

    void deleteById(Long id);

    ModificationsDto getById(Long id) throws EntityNotFoundException;

    EnginesDto getEngin(Long id) throws EntityNotFoundException;

    TransmissionsDto getTransmission(Long id) throws EntityNotFoundException;

    List<CompleteSetsDto> getListCompl(Long id) throws EntityNotFoundException;

}
