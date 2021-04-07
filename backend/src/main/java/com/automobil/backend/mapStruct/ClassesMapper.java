package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.ClassesDto;
import com.automobil.backend.models.Classes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassesMapper {

    ClassesDto toClassesDTO(Classes classes);

    Classes toCountries(ClassesDto classesDto);
}
