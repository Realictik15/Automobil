package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.CarbodyDto;
import com.automobil.backend.models.Carbody;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarbodyMapper {

    CarbodyDto toCarBodyDTO(Carbody carbody);

    List<CarbodyDto> toCarBodyDTOs(List<Carbody> carbodies);

    Carbody toCarBody(CarbodyDto carbodyDto);
}
