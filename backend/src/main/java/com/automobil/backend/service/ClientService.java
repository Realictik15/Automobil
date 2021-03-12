package com.automobil.backend.service;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Clients;

import java.util.List;

public interface ClientService {
    List<ClientsDto> listAll();

    ClientsDto getById(Long id) throws EntityNotFoundException;

    void save(ClientsDto clientsDto);

    void update(ClientsDto clientsDto);

    List<AdvertismentDto> getUserAdvert(Long id) throws EntityNotFoundException;

    List<AdvertismentDto> getUserCompare(Long id) throws EntityNotFoundException;

    void saveUserCompare(Long idClient, Long idAdvert) throws EntityNotFoundException;

    void deleteById(Long id);
}
