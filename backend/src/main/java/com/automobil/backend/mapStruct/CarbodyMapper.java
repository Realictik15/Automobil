package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.CarbodyDto;
import com.automobil.backend.models.Carbody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarbodyMapper {

    CarbodyDto toCarBodyDTO(Carbody carbody);

    Carbody toCarBody(CarbodyDto carbodyDto);
}
