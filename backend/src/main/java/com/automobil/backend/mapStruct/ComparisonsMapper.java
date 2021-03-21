package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.ComparisonsDto;
import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.models.Comparisons;
import com.automobil.backend.models.Messages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AdvertismetMapper.class,CliensMapper.class})
public interface ComparisonsMapper {
    @Mappings({
        @Mapping(source = "advertisment", target = "advertismentDto"),
        @Mapping(source = "clients.idUser", target = "idClient")
    })
    ComparisonsDto toMomparisonsDTO(Comparisons comparisons) ;


    List<ComparisonsDto> toComparisonsDTOs(List<Comparisons> comparisons);
    @Mappings({
        @Mapping(target = "advertisment", source = "advertismentDto"),
        @Mapping(target = "clients.idUser", source = "idClient")
    })
    Comparisons toComparisons(ComparisonsDto comparisonsDto) ;

}
