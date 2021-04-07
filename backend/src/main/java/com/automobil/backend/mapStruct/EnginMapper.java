package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.models.Engines;
import com.automobil.backend.service.EnginesService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnginMapper {
    EnginesDto toEnginesDTO(Engines engines);

    List<EnginesDto> toEnginesDTOs(List<Engines> engines);

    Engines toEngines(EnginesDto enginesDto);

}
