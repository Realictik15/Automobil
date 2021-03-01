package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.models.Models;
import com.automobil.backend.service.MarksService;
import com.automobil.backend.service.ModificationsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MarksMapper.class, ClassesMapper.class})
public abstract class ModelsMapper {
    @Autowired
    private ClassesMapper classesMapperr;
    @Autowired
    private MarksService marksService;

    @Mappings({
        @Mapping(source = "mark.title", target = "markTitle"),
        @Mapping(source = "classes", target = "classesDto")
    })
    public abstract ModelDto toModelDTO(Models model) ;


    public abstract List<ModelDto> toModelDTOs(List<Models> models);

    public Models toModels(ModelDto modelDto) {
        if (modelDto == null) {
            return null;
        }

        Models models = new Models();

        models.setMark(marksService.getMarkByTitle(modelDto.getMarkTitle()));
        models.setClasses(classesMapperr.toCountries(modelDto.getClassesDto()));
        models.setIdModel(modelDto.getIdModel());
        models.setTitle(modelDto.getTitle());
        models.setInfo(modelDto.getInfo());
        models.setImage(modelDto.getImage());

        return models;
    }
}
