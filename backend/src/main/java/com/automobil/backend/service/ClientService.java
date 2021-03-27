package com.automobil.backend.service;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.dto.ComparisonsDto;
import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.exeption.CLientException;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Clients;

import java.util.List;

public interface ClientService {
    List<ClientsDto> listAll();

    ClientsDto getById(Long id) throws EntityNotFoundException;

    Clients findByUserName(String name);

    void save(ClientsDto clientsDto);

    void register(ClientsDto clientsDto) throws CLientException;

    void update(ClientsDto clientsDto) throws EntityNotFoundException;

    List<AdvertismentDto> getUserAdvert(Long id) throws EntityNotFoundException;

    List<ComparisonsDto> getUserCompareDto(Long id) throws EntityNotFoundException;

    void saveUserCompare(Long idClient, Long idAdvert) throws EntityNotFoundException;

    void addMessage(MessagesDto messagesDto) throws EntityNotFoundException;

    void deleteCompare(Long id);

    void deleteById(Long id);
}
