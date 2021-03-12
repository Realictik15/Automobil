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
public interface ModelsMapper {


    @Mappings({
        @Mapping(source = "mark.title", target = "markTitle"),
        @Mapping(source = "classes", target = "classesDto")
    })
 ModelDto toModelDTO(Models model) ;


     List<ModelDto> toModelDTOs(List<Models> models);
    @Mappings({
        @Mapping(target = "mark.title", source = "markTitle"),
        @Mapping(target = "classes", source = "classesDto")
    })
     Models toModels(ModelDto modelDto) ;

}
