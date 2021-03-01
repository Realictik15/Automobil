package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.models.Generations;
import com.automobil.backend.models.Modifications;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenerationsMapper.class, EnginMapper.class, TransmissionMapper.class})
public interface ModificationMapper {
    @Mappings({@Mapping(source = "generations", target = "generationsDto"),
        @Mapping(source = "engines", target = "enginesDto"),
        @Mapping(source = "transmissions", target = "transmissionsDto")
    })
    ModificationsDto toModificationDTO(Modifications modification);

    List<ModificationsDto> toModificationDTOs(List<Modifications> modifications);

    @Mappings({@Mapping(target = "generations", source = "generationsDto"),
        @Mapping(target = "engines", source = "enginesDto"),
        @Mapping(target = "transmissions", source = "transmissionsDto")
    })
    Modifications toModification(ModificationsDto modificationsDto);
}
