package com.automobil.backend.service;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.dto.ComparisonsDto;
import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.exeption.CLientException;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Clients;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    List<ClientsDto> listAll();

    ClientsDto getById(Long id) throws EntityNotFoundException;

    Clients findByUserName(String name);

    void save(ClientsDto clientsDto);

    void register(ClientsDto clientsDto) throws CLientException;

    void update(ClientsDto clientsDto) throws EntityNotFoundException, CLientException;

    List<AdvertismentDto> getUserAdvert(Long id) throws EntityNotFoundException;

    Page<ComparisonsDto> getUserPageCompareDto(Long id, Integer page, Integer size) throws EntityNotFoundException;

    List<ComparisonsDto> getUserListCompareDto(Long id) throws EntityNotFoundException;

    void saveUserCompare(Long idClient, Long idAdvert) throws EntityNotFoundException;

    void addMessage(MessagesDto messagesDto) throws EntityNotFoundException;

    void deleteCompare(Long id);

    void deleteById(Long id);
}
