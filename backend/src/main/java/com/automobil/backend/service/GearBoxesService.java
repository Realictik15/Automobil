package com.automobil.backend.service;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.GearBoxesDto;
import com.automobil.backend.models.GearBoxes;

import java.util.List;

public interface GearBoxesService {
    List<GearBoxes> listAll();

    void save(GearBoxesDto gearBoxesDto);

    void deleteById(Long id);

    GearBoxesDto getById(Long id);

}
