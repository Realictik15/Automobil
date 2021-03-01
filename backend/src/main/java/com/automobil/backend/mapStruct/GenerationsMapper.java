package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.GearBoxesDto;
import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.models.GearBoxes;
import com.automobil.backend.models.Generations;
import com.automobil.backend.service.ModelsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ModelsMapper.class})
public abstract class GenerationsMapper {

    @Autowired
    private ModelsService modelsService;

    @Mappings({
        @Mapping(source = "model.title", target = "modelTitle")
    })
    public abstract GenerationsDto toGenerationsDTO(Generations generations);

    public abstract List<GenerationsDto> toGenerationsDTOs(List<Generations> generations);

    public Generations toGenerations(GenerationsDto generationsDto) {
        if (generationsDto == null) {
            return null;
        }

        Generations generations = new Generations();

        generations.setIdGen(generationsDto.getIdGen());
        generations.setModel(modelsService.getMarkByTitle(generationsDto.getModelTitle()));
        generations.setTitle(generationsDto.getTitle());
        generations.setYearStartDate(generationsDto.getYearStartDate());
        generations.setYearEndDate(generationsDto.getYearEndDate());
        generations.setClassOfCar(generationsDto.getClassOfCar());
        generations.setImage(generationsDto.getImage());

        return generations;
    }
}
