package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.CompleteSetsDto;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.CompleteSets;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MarksMapper.class, ModelsMapper.class, GenerationsMapper.class, ModificationMapper.class, CarbodyMapper.class, CliensMapper.class})
public  interface AdvertismetMapper {

    @Mappings({
        @Mapping(source = "carbody.title", target = "carbodyTitle"),
        @Mapping(source = "clients", target = "clientsDto"),
        @Mapping(source = "mark.title", target = "marksTitle"),
        @Mapping(source = "model.title", target = "modelTitle"),
        @Mapping(source = "generations", target = "generationsDto"),
        @Mapping(source = "modification", target = "modificationsDto")

    })
    AdvertismentDto toAdvertismentDTO(Advertisments advertisments);

    List<AdvertismentDto> toAdvertismentDTOs(List<Advertisments> advertisments);

    @Mappings({
        @Mapping(target = "carbody.title", source = "carbodyTitle"),
        @Mapping(target = "clients", source = "clientsDto"),
        @Mapping(target = "mark.title", source = "marksTitle"),
        @Mapping(target = "model.title", source = "modelTitle"),
        @Mapping(target = "generations", source = "generationsDto"),
        @Mapping(target = "modification", source = "modificationsDto")

    })
    Advertisments toAdvertisment(AdvertismentDto advertismentDto);
}
