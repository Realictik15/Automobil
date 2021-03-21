package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.models.CompleteSets;
import com.automobil.backend.models.Generations;
import com.automobil.backend.models.Modifications;
import com.automobil.backend.models.Possibles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {GenerationsMapper.class, EnginMapper.class, TransmissionMapper.class})
public abstract class ModificationMapper {
//    @Mappings({@Mapping(source = "generations", target = "generationsDto"),
//        @Mapping(source = "engines", target = "enginesDto"),
//        @Mapping(source = "possibles.idPossible", target = "idComplSet"),
//        @Mapping(source = "transmissions", target = "transmissionsDto")
//    })
//    ModificationsDto toModificationDTO(Modifications modification);
//
//    List<ModificationsDto> toModificationDTOs(List<Modifications> modifications);
//
//    @Mappings({@Mapping(target = "generations", source = "generationsDto"),
//        @Mapping(target = "engines", source = "enginesDto"),
//        @Mapping(target = "possibles.idPossible", source = "idComplSet"),
//        @Mapping(target = "transmissions", source = "transmissionsDto")
//    })
//    Modifications toModification(ModificationsDto modificationsDto);

    @Autowired
    private GenerationsMapper generationsMapper;
    @Autowired
    private EnginMapper enginMapper;
    @Autowired
    private TransmissionMapper transmissionMapper;

    public ModificationsDto toModificationDTO(Modifications modification) {
        if (modification == null) {
            return null;
        }

        ModificationsDto modificationsDto = new ModificationsDto();

        modificationsDto.setGenerationsDto(generationsMapper.toGenerationsDTO(modification.getGenerations()));
        modificationsDto.setEnginesDto(enginMapper.toEnginesDTO(modification.getEngines()));
        List<Possibles> list = modification.getPossibles();
        if (list != null) {
            modificationsDto.setIdComplSet(list.stream().map(Possibles::getCompletesets).
                map(CompleteSets::getIdCompl).collect(Collectors.toList()));
        }
        modificationsDto.setTransmissionsDto(transmissionMapper.toTransmissionDTO(modification.getTransmissions()));
        modificationsDto.setIdModif(modification.getIdModif());
        modificationsDto.setTitle(modification.getTitle());
        modificationsDto.setSits(modification.getSits());
        modificationsDto.setDoors(modification.getDoors());

        return modificationsDto;
    }

    public List<ModificationsDto> toModificationDTOs(List<Modifications> modifications) {
        if (modifications == null) {
            return null;
        }

        List<ModificationsDto> list = new ArrayList<ModificationsDto>(modifications.size());
        for (Modifications modifications1 : modifications) {
            list.add(toModificationDTO(modifications1));
        }

        return list;
    }

    public Modifications toModification(ModificationsDto modificationsDto) {
        if (modificationsDto == null) {
            return null;
        }

        Modifications modifications = new Modifications();

        modifications.setGenerations(generationsMapper.toGenerations(modificationsDto.getGenerationsDto()));
        modifications.setEngines(enginMapper.toEngines(modificationsDto.getEnginesDto()));
        modifications.setTransmissions(transmissionMapper.toTransmissions(modificationsDto.getTransmissionsDto()));
        modifications.setIdModif(modificationsDto.getIdModif());
        modifications.setTitle(modificationsDto.getTitle());
        modifications.setSits(modificationsDto.getSits());
        modifications.setDoors(modificationsDto.getDoors());

        return modifications;
    }
}
