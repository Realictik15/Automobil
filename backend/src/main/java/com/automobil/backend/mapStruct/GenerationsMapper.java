package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.models.Generations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ModelsMapper.class})
public interface GenerationsMapper {

    @Mappings({
        @Mapping(source = "model.title", target = "modelTitle")
    })
    GenerationsDto toGenerationsDTO(Generations generations);

     List<GenerationsDto> toGenerationsDTOs(List<Generations> generations);
    @Mappings({
        @Mapping(target = "model.title", source = "modelTitle")
    })
    Generations toGenerations(GenerationsDto generationsDto) ;
}
