package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.GearBoxesDto;
import com.automobil.backend.dto.TransmissionsDto;
import com.automobil.backend.models.GearBoxes;
import com.automobil.backend.models.Transmissions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GearBoxesMapper.class})
public interface TransmissionMapper {

    @Mapping(source = "gearbox", target = "gearBoxDto")
    TransmissionsDto toTransmissionDTO(Transmissions transmissions);

    List<TransmissionsDto> toTransmissionDTOs(List<Transmissions> transmissions);

    @Mapping(source = "gearBoxDto", target = "gearbox")
    Transmissions toTransmissions(TransmissionsDto transmissionsDto);
}
