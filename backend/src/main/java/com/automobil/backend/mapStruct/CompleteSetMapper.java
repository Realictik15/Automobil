package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.dto.MarksDto;
import com.automobil.backend.models.CompleteSets;
import com.automobil.backend.models.Marks;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompleteSetMapper {

    CompleteSetsDto toCompleteSetsDTO(CompleteSets completeSets);

    List<CompleteSetsDto> toCompleteSetsDTOs(List<CompleteSets> completeSets);

    CompleteSets toCompleteSets(CompleteSetsDto completeSetsDto);
}
