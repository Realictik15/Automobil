package com.automobil.backend.service;

import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Clients;

import java.util.List;

public interface ClientService {
    List<ClientsDto> listAll();

    ClientsDto getById(Long id);

    Clients save(ClientsDto clientsDto);

    void update(ClientsDto clientsDto);

    void delete(Clients clients);

    void deleteById(Long id);
}
