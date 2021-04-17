package com.automobil.backend.mapStruct;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Clients;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel="spring")
public interface CliensMapper {
 
    ClientsDto toClientsDTO(Clients clients);

    List<ClientsDto> toClientsDTOs(List<Clients> clients);

    Clients toClients(ClientsDto clientsDto);
}
