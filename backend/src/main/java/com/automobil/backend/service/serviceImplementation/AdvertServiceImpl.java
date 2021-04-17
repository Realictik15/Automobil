package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.FiltersDto;
import com.automobil.backend.dto.FormAdvert;
import com.automobil.backend.exeption.AdvertExeption;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.AdvertismetMapper;
import com.automobil.backend.mapStruct.FormAdvertMapper;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.models.Generations;
import com.automobil.backend.repository.*;
import com.automobil.backend.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Value("${upload.path}")
    private String uploadpath;

    private final AdvertismetMapper advertismetMapper;
    private final AdvertisRepository advertisRepository;
    private final GenerationsRepository generationsRepository;
    private final ClientsRepository clientsRepository;
    private final CarbodyRepository carbodyRepository;
    private final MarksRepository marksRepository;
    private final ModelsRepository modelsRepository;
    private final ModificationsRepository modificationsRepository;
    private final FormAdvertMapper formAdvertMapper;

    @Autowired
    public AdvertServiceImpl(AdvertismetMapper advertismetMapper, AdvertisRepository advertisRepository,
                             GenerationsRepository generationsRepository,
                             ClientsRepository clientsRepository, CarbodyRepository carbodyRepository,
                             MarksRepository marksRepository, ModelsRepository modelsRepository,
                             ModificationsRepository modificationsRepository, FormAdvertMapper formAdvertMapper) {
        this.advertismetMapper = advertismetMapper;
        this.advertisRepository = advertisRepository;
        this.generationsRepository = generationsRepository;
        this.clientsRepository = clientsRepository;
        this.carbodyRepository = carbodyRepository;
        this.marksRepository = marksRepository;
        this.modelsRepository = modelsRepository;
        this.modificationsRepository = modificationsRepository;
        this.formAdvertMapper = formAdvertMapper;
    }

    @Override
    public List<AdvertismentDto> getlistAll() {
        List<Advertisments> advertisments = advertisRepository.findAll();
        return advertismetMapper.toAdvertismentDTOs(advertisments);
    }

    @Override
    public Page<AdvertismentDto> getListAllAvaliblePage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Advertisments> advertisments = advertisRepository.getListAllAvaliblePage(pageRequest);
        int totalElements = (int) advertisments.getTotalElements();
        return new PageImpl<>(advertisments.stream().map(advertismetMapper::toAdvertismentDTO).collect(Collectors.toList()), pageRequest, totalElements);
    }

    public Page<AdvertismentDto> getListAllFilPage(int page, int size, FiltersDto filtersDto) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Advertisments> advertisments = advertisRepository.getListAllAvaliblePage(pageRequest);
        int totalElements = (int) advertisments.getTotalElements();
        return new PageImpl<>(advertisments.stream().map(advertismetMapper::toAdvertismentDTO).collect(Collectors.toList()), pageRequest, totalElements);
    }


    @Override
    public List<AdvertismentDto> getListByClass(Long id) {
        List<Advertisments> advertisments = advertisRepository.getListByClass(id);
        return advertismetMapper.toAdvertismentDTOs(advertisments);
    }

    @Override
    public Page<AdvertismentDto> getListFilters(FiltersDto filtersDto, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

       if( filtersDto.getCarBody()==null){
           filtersDto.setCarBody("all");
       }
        if(filtersDto.getMileage()==null){
            filtersDto.setMileage(-1);
        }
        if(filtersDto.getMark()==null){
            filtersDto.setMark("all");
        }
        if(filtersDto.getModel()==null){
            filtersDto.setModel("all");
        }
       if( filtersDto.getDateEnd()==null){
           String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
           filtersDto.setDateEnd(date);
       }
        if(filtersDto.getDateStart()==null){
            filtersDto.setDateStart("01/01/1900");
        }
        if (filtersDto.getPriceStart()==null){
            filtersDto.setPriceStart(0L);
        }
        if(filtersDto.getPriceEnd()==null){
            filtersDto.setPriceEnd((long) -1);
        }
        if(filtersDto.getGearBox()==null){
            filtersDto.setGearBox("all");
        }
        Page<Advertisments> advertisments = advertisRepository.getListFilters(pageRequest,filtersDto.getMileage(),
            filtersDto.getMark(),filtersDto.getModel(),filtersDto.getCarBody(),filtersDto.getDateStart(),filtersDto.getDateEnd(),
            filtersDto.getPriceStart(),filtersDto.getPriceEnd(),filtersDto.getGearBox());

        int totalElements = (int) advertisments.getTotalElements();
        return new PageImpl<>(advertisments.stream().map(advertismetMapper::toAdvertismentDTO).collect(Collectors.toList()), pageRequest, totalElements);
 //  return null;
    }

    @Override
    public AdvertismentDto getById(Long id) throws EntityNotFoundException {
        Advertisments advertisments = advertisRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Advertisments"));
        return advertismetMapper.toAdvertismentDTO(advertisments);
    }


    @Override
    public List<AdvertismentDto> getlistAvalible() {
        List<Advertisments> advertisments = advertisRepository.getListAllAvalible();
        return advertismetMapper.toAdvertismentDTOs(advertisments);
    }


    @Override
    public List<AdvertismentDto> getReport(Long id) throws EntityNotFoundException {
        Advertisments advertisment = advertisRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Advertisments"));
        List<Advertisments> reports = advertisRepository.getReportItems(advertisment.getVin());

        return advertismetMapper.toAdvertismentDTOs(reports);
    }

    @Override
    public Advertisments getByIdAdvert(Long id) throws EntityNotFoundException {
        return advertisRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Advertisments"));
    }

    @Override
    public void deleteById(Long id) {
        advertisRepository.deleteById(id);
    }


    @Override
    public void userDeleteById(Long id) throws EntityNotFoundException {
        Advertisments advertisment = advertisRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Advertisments"));
        advertisment.setClients(clientsRepository.findById((long) 1).
            orElseThrow(javax.persistence.EntityNotFoundException::new));// присваиваю обявление админу для того, чтобы не удалять объявление, доступность объявления ставлю no
        advertisment.setAvailable("no");
        advertisRepository.save(advertisment);
    }

    @Override
    public List<String> saveUploadedFiles(List<MultipartFile> files) throws IOException {
        List<String> result = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            byte[] bytes = file.getBytes();
            String id = UUID.randomUUID().toString();
            String savepath = uploadpath + id + file.getOriginalFilename();
            Path path = Paths.get(savepath);
            Files.write(path, bytes);
            result.add(savepath);
        }
        return result;
    }

    @Override
    public void save(FormAdvert formAdvert, List<String> list) throws
        EntityNotFoundException, ParseException {
        AdvertismentDto advertismentDto = formAdvertMapper.toAdvertismentDTO(formAdvert);
        Advertisments advert = advertismetMapper.toAdvertisment(advertismentDto);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                s.append(list.get(i));
            } else {
                s.append(list.get(i)).append(",");
            }
        }
        advert.setImages(s.toString());
        advert.setClients(clientsRepository.findById(formAdvert.getClientsId()).orElseThrow(EntityNotFoundException::new));
        advert.setCarbody(carbodyRepository.getCarBodyByTitle(formAdvert.getCarbodyTitle()).orElseThrow(EntityNotFoundException::new));
        advert.setMark(marksRepository.getMarkByTitle(formAdvert.getMarksTitle()).orElseThrow(EntityNotFoundException::new));
        advert.setModel(modelsRepository.getModelsByTitle(formAdvert.getModelTitle()).orElseThrow(EntityNotFoundException::new));
        Generations gen=generationsRepository.findById(formAdvert.getGenerationsId()).orElseThrow(EntityNotFoundException::new);
        advert.setGenerations(gen);
        advert.setModification(modificationsRepository.findById(formAdvert.getModificationsId()).orElseThrow(EntityNotFoundException::new));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            advert.setDayofwarranty(format.parse(formAdvert.getDayofwarrantys()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            Date tmp = format.parse(formAdvert.getBuydays());
            if(tmp.before(gen.getYearEndDate())&&tmp.after(gen.getYearStartDate())){
                advert.setBuyday(tmp);

            }else {
              throw new AdvertExeption("the year goes beyond the machine generation");
            }
        } catch (ParseException | AdvertExeption e) {
            e.printStackTrace();
        }

//        advertisRepository.save(advert);
    }

    @Override
    public void update(AdvertismentDto advertismentDto) throws EntityNotFoundException {
        Advertisments newadvertisments = advertisRepository.findById(advertismentDto.getIdAdvert()).orElseThrow(EntityNotFoundException::new);
        newadvertisments.setPrice(advertismentDto.getPrice());
        newadvertisments.setCity(advertismentDto.getCity());
        newadvertisments.setPlace(advertismentDto.getPlace());
        newadvertisments.setPhone(advertismentDto.getPhone());
        newadvertisments.setSwap(advertismentDto.getSwap());
        newadvertisments.setCommentns(advertismentDto.getCommentns());
        advertisRepository.save(newadvertisments);
    }

}
