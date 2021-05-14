package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.ClientsDto;
import com.automobil.backend.dto.ComparisonsDto;
import com.automobil.backend.dto.MessagesDto;
import com.automobil.backend.exeption.CLientException;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.AdvertismetMapper;
import com.automobil.backend.mapStruct.CliensMapper;
import com.automobil.backend.mapStruct.ComparisonsMapper;
import com.automobil.backend.models.*;
import com.automobil.backend.repository.*;
import com.automobil.backend.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsServiceImpl implements ClientService {
    private final ClientsRepository clientsRepository;
    private final CliensMapper cliensMapper;
    private final AdvertismetMapper advertismetMapper;
    private final AdvertisRepository advertisRepository;
    private final ComparisonsRepository comparisonsRepository;
    private final ComparisonsMapper comparisonsMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ReviewsRepository reviewsRepository;
    private final MessagesRepository messagesRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsServiceImpl.class);

    @Autowired
    public ClientsServiceImpl(ClientsRepository clientsRepository, CliensMapper cliensMapper,
                              AdvertismetMapper advertismetMapper, AdvertisRepository advertisRepository,
                              ComparisonsRepository comparisonsRepository, ComparisonsMapper comparisonsMapper, BCryptPasswordEncoder passwordEncoder, ReviewsRepository reviewsRepository, MessagesRepository messagesRepository) {
        this.clientsRepository = clientsRepository;
        this.cliensMapper = cliensMapper;
        this.advertismetMapper = advertismetMapper;
        this.advertisRepository = advertisRepository;
        this.comparisonsRepository = comparisonsRepository;
        this.comparisonsMapper = comparisonsMapper;
        this.passwordEncoder = passwordEncoder;
        this.reviewsRepository = reviewsRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<ClientsDto> listAll() {
        LOGGER.info("Received all users");
        return cliensMapper.toClientsDTOs(clientsRepository.findAll().stream().sorted(Comparator.comparing(Clients::getIdUser)).collect(Collectors.toList()));
    }

    @Override
    public ClientsDto getById(Long id) throws EntityNotFoundException {
        LOGGER.info("Received user by id:{}", id);
        return cliensMapper.toClientsDTO(clientsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Client")));
    }

    @Override
    public Clients findByUserName(String name) {
        LOGGER.info("Received user by login:{}", name);
        return clientsRepository.getByLogin(name);
    }

    @Override
    public void save(ClientsDto clientsDto) {
        Clients client = cliensMapper.toClients(clientsDto);
        clientsRepository.save(client);
        LOGGER.info("User saved");
    }

    @Override
    public void register(ClientsDto clientsDto) throws CLientException {
        if (clientsRepository.existsByLoginIs(clientsDto.getLogin()) > 0) {
            throw new CLientException("login");
        }
        if (clientsRepository.existsByEmaleIs(clientsDto.getEmale()) > 0) {
            throw new CLientException("email");
        }
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
        LOGGER.info("User is registered with login:{}, email:{}", client.getLogin(), client.getEmale());
    }

    @Override
    public void update(ClientsDto clientsDto) throws EntityNotFoundException, CLientException {
        Clients newClient = clientsRepository.findById(clientsDto.getIdUser()).orElseThrow(EntityNotFoundException::new);

        if (!clientsDto.getEmale().equals(newClient.getEmale())) {
            if (clientsRepository.existsByEmaleIs(clientsDto.getEmale()) > 0) {
                throw new CLientException("emale");
            } else {
                newClient.setEmale(clientsDto.getEmale());
            }
        }
        newClient.setFirstName(clientsDto.getFirstName());
        newClient.setLastName(clientsDto.getLastName());
        newClient.setBornDay(clientsDto.getBornDay());
        newClient.setTelephone(clientsDto.getTelephone());
        newClient.setDriveExp(clientsDto.getDriveExp());
        clientsRepository.save(newClient);
    }

    @Override
    public List<ComparisonsDto> getUserListCompareDto(Long id) throws EntityNotFoundException {
        Clients client = clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Clients"));

        return comparisonsMapper.toComparisonsDTOs(client.getComparisons());
    }

    @Override
    public Page<ComparisonsDto> getUserPageCompareDto(Long id, int page, int size) throws EntityNotFoundException {
        PageRequest pageRequest = PageRequest.of(page, size);
        Clients client = clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Clients"));
        List<ComparisonsDto> list = comparisonsMapper.toComparisonsDTOs(client.getComparisons()).stream().sorted(Comparator.comparing(a -> a.getAdvertismentDto().getIdAdvert())).collect(Collectors.toList());
        int start = (int) pageRequest.getOffset();
        int end = (Math.min((start + pageRequest.getPageSize()), list.size()));
        LOGGER.info("Received page with user compare page:{},size:{},elements:{}", page, size, list.size());
        return new PageImpl<>(list.subList(start, end), pageRequest, list.size());
    }

    @Override
    public List<AdvertismentDto> getUserAdvert(Long id) throws EntityNotFoundException {
        LOGGER.info("Receiving adverts by user id:{}", id);
        return advertismetMapper.toAdvertismentDTOs(clientsRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Clients")).getAdvertisments().stream().sorted(Comparator.comparing(Advertisments::getIdAdvert)).collect(Collectors.toList()));
    }

    @Override
    public void saveUserCompare(Long idClient, Long idAdvert) throws EntityNotFoundException {
        Advertisments advertisment = advertisRepository.findById(idAdvert).orElseThrow(EntityNotFoundException::new);
        Clients clients = clientsRepository.findById(idClient).orElseThrow(() -> new EntityNotFoundException(idClient, "Clients"));
        comparisonsRepository.save(new Comparisons(null, advertisment, clients));
        LOGGER.info("New compare saved, client id:{}, advert id:{}", idClient, idAdvert);
    }

    @Override
    public void addMessage(MessagesDto messagesDto) throws EntityNotFoundException {
        LOGGER.info("future functionality");
        Messages message = new Messages();
        message.setReviews(reviewsRepository.findById(messagesDto.getReviewsDto().getIdRevi()).orElseThrow(EntityNotFoundException::new));
        message.setClients(clientsRepository.findById(messagesDto.getClientsDto().getIdUser()).orElseThrow(EntityNotFoundException::new));
        message.setText(messagesDto.getText());
        message.setDateSend(messagesDto.getDateSend());
        messagesRepository.save(message);

    }

    @Override
    public void deleteCompare(Long id) {
        comparisonsRepository.deleteById(id);
        LOGGER.info("Compare with id {} deleted", id);
    }


    @Override
    public void deleteById(Long id) {
        clientsRepository.deleteById(id);
        LOGGER.info("Client with id {} deleted", id);
    }

}
