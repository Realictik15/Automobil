package com.automobil.backend.service;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.GearBoxesDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.GearBoxes;

import java.util.List;

public interface GearBoxesService {
    GearBoxesDto getById(Long id) throws EntityNotFoundException;

    List<GearBoxesDto> getListGearBox();

    GearBoxes toGearBox(GearBoxesDto gearBoxesDto);
}
