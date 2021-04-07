package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.FormAdvert;
import com.automobil.backend.models.Advertisments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AdvertismetMapper.class, MarksMapper.class, ModelsMapper.class, GenerationsMapper.class, ModificationMapper.class, CarbodyMapper.class, CliensMapper.class})
public interface FormAdvertMapper {
    @Mappings({
        @Mapping(source = "clientsDto.idUser", target = "clientsId"),
        @Mapping(source = "generationsDto.idGen", target = "generationsId"),
        @Mapping(source = "modificationsDto.idModif", target = "modificationsId")

    })
    FormAdvert toFormAdvert(AdvertismentDto advertismentDto);

    @Mappings({
        @Mapping(target = "clientsDto.idUser", source = "clientsId"),
        @Mapping(target = "generationsDto.idGen", source = "generationsId"),
        @Mapping(target = "modificationsDto.idModif", source = "modificationsId")

    })
    AdvertismentDto toAdvertismentDTO(FormAdvert formAdvert);
}


