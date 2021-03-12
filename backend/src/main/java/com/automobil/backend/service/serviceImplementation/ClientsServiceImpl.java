package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.AdvertismetMapper;
import com.automobil.backend.mapStruct.CliensMapper;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Clients;
import com.automobil.backend.models.Comparisons;
import com.automobil.backend.repository.ClientsRepository;

import com.automobil.backend.repository.ComparisonsRepository;
import com.automobil.backend.service.AdvertService;
import com.automobil.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsServiceImpl implements ClientService {
    private final ClientsRepository clientsRepository;
    private final CliensMapper cliensMapper;
    private final AdvertismetMapper advertismetMapper;
    private final AdvertService advertService;
    private final ComparisonsRepository comparisonsRepository;

    @Autowired
    public ClientsServiceImpl(ClientsRepository clientsRepository, CliensMapper cliensMapper, AdvertismetMapper advertismetMapper, AdvertService advertService, ComparisonsRepository comparisonsRepository) {
        this.clientsRepository = clientsRepository;
        this.cliensMapper = cliensMapper;
        this.advertismetMapper = advertismetMapper;
        this.advertService = advertService;
        this.comparisonsRepository = comparisonsRepository;
    }

    @Override
    public List<ClientsDto> listAll() {
        return null;
    }

    @Override
    public ClientsDto getById(Long id) throws EntityNotFoundException {
        return cliensMapper.toClientsDTO(clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Client")));
    }

    @Override
    public void save(ClientsDto clientsDto) {
        clientsRepository.save(cliensMapper.toClients(clientsDto));
    }

    @Override
    public void update(ClientsDto clientsDto) {
   //подумать надо
    }

    @Override
    public List<AdvertismentDto> getUserAdvert(Long id) throws EntityNotFoundException {
        Clients clients = clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Clients"));
        return advertismetMapper.toAdvertismentDTOs(clients.getComparisons().stream().map(Comparisons::getAdvertisment).collect(Collectors.toList()));
    }

    @Override
    public List<AdvertismentDto> getUserCompare(Long id) throws EntityNotFoundException {
        return advertismetMapper.toAdvertismentDTOs(clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Clients")).getAdvertisments());
    }

    @Override
    public void saveUserCompare(Long idClient, Long idAdvert) throws EntityNotFoundException {
        Advertisments advertisment = advertService.getByIdAdvert(idAdvert);
        Clients clients = clientsRepository.findById(idClient).orElseThrow(() -> new EntityNotFoundException(idClient, "Clients"));
        comparisonsRepository.save(new Comparisons(null, advertisment, clients));
    }


    @Override
    public void deleteById(Long id) {
        clientsRepository.deleteById(id);
    }
}
