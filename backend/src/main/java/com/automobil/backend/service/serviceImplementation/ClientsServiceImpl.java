package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.mapStruct.EnginMapper;
import com.automobil.backend.models.Clients;
import com.automobil.backend.repository.ClientsRepository;

import com.automobil.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientsServiceImpl implements ClientService {
    private final ClientsRepository clientsRepository;
    private final EnginMapper enginMapper;

    @Autowired
    public ClientsServiceImpl(ClientsRepository clientsRepository, EnginMapper enginMapper) {
        this.clientsRepository = clientsRepository;
        this.enginMapper = enginMapper;
    }

    @Override
    public List<ClientsDto> listAll() {
        return null;
    }

    @Override
    public ClientsDto getById(Long id) {
        return null;
    }

    @Override
    public Clients save(ClientsDto clientsDto) {
        return null;
    }

    @Override
    public void update(ClientsDto clientsDto) {

    }

    @Override
    public void delete(Clients clients) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
