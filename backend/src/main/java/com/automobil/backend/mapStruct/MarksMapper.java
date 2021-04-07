package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.CountriesDto;
import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.models.Countries;
import com.automobil.backend.models.Marks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CountriesMapper.class})
public interface MarksMapper {

    @Mapping(source = "countries.title", target = "countriesDto")
    MarksDto toMarksDTO(Marks marks);

    List<MarksDto> toMarksDTOs(List<Marks> marks);

    @Mapping(source = "countriesDto", target = "countries.title")
    Marks toMarks(MarksDto marksDto);

}
