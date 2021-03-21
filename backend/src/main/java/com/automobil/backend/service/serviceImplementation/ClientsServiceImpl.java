package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.dto.ComparisonsDto;
import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.AdvertismetMapper;
import com.automobil.backend.mapStruct.CliensMapper;
import com.automobil.backend.mapStruct.ComparisonsMapper;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Clients;
import com.automobil.backend.models.Comparisons;
import com.automobil.backend.models.Roles;
import com.automobil.backend.repository.AdvertisRepository;
import com.automobil.backend.repository.ClientsRepository;
import com.automobil.backend.repository.ComparisonsRepository;
import com.automobil.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsServiceImpl implements ClientService {
    private final ClientsRepository clientsRepository;
    private final CliensMapper cliensMapper;
    private final AdvertismetMapper advertismetMapper;
    private final AdvertisRepository advertisRepository;
    private final ComparisonsRepository comparisonsRepository;
    private final ComparisonsMapper comparisonsMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ClientsServiceImpl(ClientsRepository clientsRepository, CliensMapper cliensMapper,
                              AdvertismetMapper advertismetMapper, AdvertisRepository advertisRepository,
                              ComparisonsRepository comparisonsRepository, ComparisonsMapper comparisonsMapper, BCryptPasswordEncoder passwordEncoder) {
        this.clientsRepository = clientsRepository;
        this.cliensMapper = cliensMapper;
        this.advertismetMapper = advertismetMapper;
        this.advertisRepository = advertisRepository;
        this.comparisonsRepository = comparisonsRepository;
        this.comparisonsMapper = comparisonsMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ClientsDto> listAll() {
        return cliensMapper.toClientsDTOs(clientsRepository.findAll());
    }

    @Override
    public ClientsDto getById(Long id) throws EntityNotFoundException {
        return cliensMapper.toClientsDTO(clientsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Client")));
    }

    @Override
    public Clients findByUserName(String name) {
        return clientsRepository.getByLogin(name);
    }

    @Override
    public void save(ClientsDto clientsDto) {
        Clients client = cliensMapper.toClients(clientsDto);
        clientsRepository.save(client);
    }

    @Override
    public void register(ClientsDto clientsDto) {
//        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
//            throw new UsernameIsAlreadyTaken();
//        }
//        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
//            throw new EmailIsAlreadyExists();
//        }
        // Create new user's account
        Clients client = new Clients();
        client.setFirstName(clientsDto.getFirstName());
        client.setLastName(clientsDto.getLastName());
        client.setBornDay(clientsDto.getBornDay());
        client.setTelephone(clientsDto.getTelephone());
        client.setDriveExp(clientsDto.getDriveExp());
        client.setLogin(clientsDto.getLogin());
        client.setEmale(clientsDto.getEmale());
        client.setPass(passwordEncoder.encode(clientsDto.getPass()));
        client.setRoles(Roles.USER);
        clientsRepository.save(client);

    }

    @Override
    public void update(ClientsDto clientsDto) throws EntityNotFoundException {
        Clients newClient = clientsRepository.findById(clientsDto.getIdUser()).orElseThrow(EntityNotFoundException::new);
        newClient.setFirstName(clientsDto.getFirstName());
        newClient.setLastName(clientsDto.getLastName());
        newClient.setBornDay(clientsDto.getBornDay());
        newClient.setEmale(clientsDto.getEmale());
        newClient.setTelephone(clientsDto.getTelephone());
        newClient.setDriveExp(clientsDto.getDriveExp());
        clientsRepository.save(newClient);
    }

//    @Override
//    public List<AdvertismentDto> getUserCompare(Long id) throws EntityNotFoundException {
//        Clients clients = clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Clients"));
//        return advertismetMapper.toAdvertismentDTOs(clients.getComparisons().stream().
//            map(Comparisons::getAdvertisment).collect(Collectors.toList()));
//
//    }

    @Override
    public List<ComparisonsDto> getUserCompareDto(Long id) throws EntityNotFoundException {
        Clients clients = clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Clients"));
        return comparisonsMapper.toComparisonsDTOs(clients.getComparisons());
    }

    @Override
    public List<AdvertismentDto> getUserAdvert(Long id) throws EntityNotFoundException {
        return advertismetMapper.toAdvertismentDTOs(clientsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Clients")).getAdvertisments());
    }

    @Override
    public void saveUserCompare(Long idClient, Long idAdvert) throws EntityNotFoundException {
        Advertisments advertisment = advertisRepository.findById(idAdvert).orElseThrow(EntityNotFoundException::new);
        Clients clients = clientsRepository.findById(idClient).orElseThrow(() -> new EntityNotFoundException(idClient, "Clients"));
        comparisonsRepository.save(new Comparisons(null, advertisment, clients));
    }

    @Override
    public void addMessage(MessagesDto messagesDto) {

    }

    @Override
    public void deleteCompare(Long id) {
        clientsRepository.deleteById(id);
    }


    @Override
    public void deleteById(Long id) {
        clientsRepository.deleteById(id);
    }
}
