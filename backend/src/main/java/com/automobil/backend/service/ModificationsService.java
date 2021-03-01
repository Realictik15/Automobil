package com.automobil.backend.service;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.ModificationsDto;

import java.util.List;

public interface ModificationsService {
    List<ModificationsDto> listAll();

    void save(ModificationsDto modificationsDto);

    void deleteById(Long id);

    ModificationsDto getById(Long id);

    void delete(ModificationsDto modificationsDto);
}
