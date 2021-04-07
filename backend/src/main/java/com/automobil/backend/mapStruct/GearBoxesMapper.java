package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.GearBoxesDto;
import com.automobil.backend.models.Engines;
import com.automobil.backend.models.GearBoxes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GearBoxesMapper {
    GearBoxesDto toGearBoxesDTO(GearBoxes gearBoxes);

    List<GearBoxesDto> toGearBoxesDTOs(List<GearBoxes> gearBoxes);

    GearBoxes toGearBoxes(GearBoxesDto gearBoxesDto);
}
