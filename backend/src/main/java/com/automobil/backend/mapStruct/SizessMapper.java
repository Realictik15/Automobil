package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.models.Sizess;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SizessMapper {

    @Mappings({
        @Mapping(source = "bodies.carbody.idCarBody", target = "idCarBody"),
        @Mapping(source = "bodies.generations.idGen", target = "idGen")
    })
    SizessDto toSizessDTO(Sizess sizess);

    List<SizessDto> toSizessDTOs(List<Sizess> sizesses);

    @Mappings({
        @Mapping(target = "bodies.carbody.idCarBody", source = "idCarBody"),
        @Mapping(target = "bodies.generations.idGen", source = "idGen")
    })
    Sizess toSizess(SizessDto sizessDto);
}

