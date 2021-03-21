package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.ModelDto;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.models.Bodies;
import com.automobil.backend.models.Models;
import com.automobil.backend.models.Sizess;
import com.automobil.backend.repository.BodiesRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SizessMapper {


    @Mappings({
        @Mapping(source = "bodies.carbody.idCarBody", target = "idCarBody"),
        @Mapping(source = "bodies.generations.idGen", target = "idGen")
    })
    public abstract SizessDto toSizessDTO(Sizess sizess);

    List<SizessDto> toSizessDTOs(List<Sizess> sizesses);

    @Mappings({
        @Mapping(target = "bodies.carbody.idCarBody", source = "idCarBody"),
        @Mapping(target = "bodies.generations.idGen", source = "idGen")
    })
    Sizess toSizess(SizessDto sizessDto);
}

