package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.AdvertismetMapper;
import com.automobil.backend.mapStruct.SizessMapper;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.repository.AdvertisRepository;
import com.automobil.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {

    private final AdvertismetMapper advertismetMapper;
    private final AdvertisRepository advertisRepository;
    private final SizessMapper sizessMapper;
    private final ClientService clientService;
    private final CarBodyService carBodyService;
    private final MarksService marksService;
    private  final ModificationsService modificationsService;
    private final ModelsService modelsService;
    private final GenerationsService generationsService;

    @Autowired
    public AdvertServiceImpl(AdvertismetMapper advertismetMapper, AdvertisRepository advertisRepository, SizessMapper sizessMapper, ClientService clientService, CarBodyService carBodyService, MarksService marksService, ModificationsService modificationsService, ModelsService modelsService, GenerationsService generationsService) {
        this.advertismetMapper = advertismetMapper;
        this.advertisRepository = advertisRepository;
        this.sizessMapper = sizessMapper;
        this.clientService = clientService;
        this.carBodyService = carBodyService;
        this.marksService = marksService;
        this.modificationsService = modificationsService;
        this.modelsService = modelsService;
        this.generationsService = generationsService;
    }

    @Override
    public List<AdvertismentDto> getlistAll() {
        return advertismetMapper.toAdvertismentDTOs(advertisRepository.findAll());
    }

    @Override
    public List<AdvertismentDto> getlistAvalible() {
        return  advertismetMapper.toAdvertismentDTOs(advertisRepository.getListAllAvalible());
    }


    @Override
    public List<AdvertismentDto> getReport(Long id) throws EntityNotFoundException {
        Advertisments advertisment = advertisRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Advertisments"));

        return advertismetMapper.toAdvertismentDTOs(advertisRepository.getReportItems(advertisment.getVin()));
    }

    @Override
    public AdvertismentDto getById(Long id) throws EntityNotFoundException {
        return advertismetMapper.toAdvertismentDTO(advertisRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Advertisments")));
    }
    @Override
    public Advertisments getByIdAdvert(Long id) throws EntityNotFoundException {
        return advertisRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Advertisments"));
    }

    @Override
    public void save(AdvertismentDto advertismentDto) throws EntityNotFoundException {

       Advertisments advert= advertismetMapper.toAdvertisment(advertismentDto);
     //  advert.setClients(clientService.getById(advertismentDto.getClientsDto().getIdUser()));
        advertisRepository.save(advertismetMapper.toAdvertisment(advertismentDto));
    }

    @Override
    public void update(AdvertismentDto advertismentDto) {

    }

    @Override
    public void deleteById(Long id) {
        advertisRepository.deleteById(id);
    }

    @Override
    public void userDeleteById(Long id) throws EntityNotFoundException {
        Advertisments advertisment = advertisRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id,"Advertisments"));
        //advertisment.setClients();
    }

    @Override
    public SizessDto getSizeByClass(Long id) {
        return null;
    }
}
