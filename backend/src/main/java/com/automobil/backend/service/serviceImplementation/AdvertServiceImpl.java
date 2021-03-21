package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.FormAdvert;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.AdvertismetMapper;
import com.automobil.backend.mapStruct.FormAdvertMapper;
import com.automobil.backend.mapStruct.SizessMapper;
import com.automobil.backend.models.Advertisments;
import com.automobil.backend.repository.*;
import com.automobil.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Value("${upload.path}")
    private String uploadpath;

    private final AdvertismetMapper advertismetMapper;
    private final AdvertisRepository advertisRepository;
    private final SizessMapper sizessMapper;
    private final GenerationsRepository generationsRepository;
    private final ClientsRepository clientsRepository;
    private final CarbodyRepository carbodyRepository;
    private final MarksRepository marksRepository;
    private final ModelsRepository modelsRepository;
    private final ModificationsRepository modificationsRepository;
    private final FormAdvertMapper formAdvertMapper;

    @Autowired
    public AdvertServiceImpl(AdvertismetMapper advertismetMapper, AdvertisRepository advertisRepository,
                             SizessMapper sizessMapper, GenerationsRepository generationsRepository,
                             ClientsRepository clientsRepository, CarbodyRepository carbodyRepository,
                             MarksRepository marksRepository, ModelsRepository modelsRepository,
                             ModificationsRepository modificationsRepository, FormAdvertMapper formAdvertMapper) {
        this.advertismetMapper = advertismetMapper;
        this.advertisRepository = advertisRepository;
        this.sizessMapper = sizessMapper;
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
        List<AdvertismentDto> advertismentDtos = advertismetMapper.toAdvertismentDTOs(advertisments);
        for (int i = 0; i < advertismentDtos.size(); i++) {
            advertismentDtos.get(i).setImagesList((getImagesFromModel(advertisments.get(i).getImages())));
        }
        return advertismentDtos;
    }

    @Override
    public AdvertismentDto getById(Long id) throws EntityNotFoundException {
        Advertisments advertisments = advertisRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Advertisments"));
        AdvertismentDto advertismentDto = advertismetMapper.toAdvertismentDTO(advertisments);
        advertismentDto.setImagesList(getImagesFromModel(advertisments.getImages()));
        return advertismentDto;
    }

    @Override
    public List<AdvertismentDto> getlistAvalible() {
        List<Advertisments> advertisments = advertisRepository.getListAllAvalible();
        List<AdvertismentDto> advertismentDtos = advertismetMapper.toAdvertismentDTOs(advertisments);
        for (int i = 0; i < advertismentDtos.size(); i++) {
            advertismentDtos.get(i).setImagesList((getImagesFromModel(advertisments.get(i).getImages())));
        }
        return advertismentDtos;
    }


    @Override
    public List<AdvertismentDto> getReport(Long id) throws EntityNotFoundException {
        Advertisments advertisment = advertisRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException(id, "Advertisments"));

        return advertismetMapper.toAdvertismentDTOs(advertisRepository.getReportItems(advertisment.getVin()));
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
            String savepath = id + file.getOriginalFilename();
            Path path = Paths.get(uploadpath + savepath);
            Files.write(path, bytes);
            result.add(savepath);
        }
        return result;
    }

    public List<String> getImagesFromModel(String path) {
        int index = path.lastIndexOf('\\');
        String s = path.substring(index + 1);
        List<String> res = (Arrays.asList(s.split(",")));
        res.forEach(x -> x += uploadpath);
        return res;
    }

    @Override
    public void save(FormAdvert formAdvert, List<String> list) throws EntityNotFoundException, ParseException {
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
        advert.setImages(uploadpath + s);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            advert.setDayofwarranty(format.parse(formAdvert.getDayofwarrantys()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            advert.setBuyday(format.parse(formAdvert.getBuydays()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        advert.setClients(clientsRepository.findById(formAdvert.getClientsId()).orElseThrow(EntityNotFoundException::new));
        advert.setCarbody(carbodyRepository.getCarBodyByTitle(formAdvert.getCarbodyTitle()).orElseThrow(EntityNotFoundException::new));
        advert.setMark(marksRepository.getMarkByTitle(formAdvert.getMarksTitle()).orElseThrow(EntityNotFoundException::new));
        advert.setModel(modelsRepository.getModelsByTitle(formAdvert.getModelTitle()).orElseThrow(EntityNotFoundException::new));
        advert.setGenerations(generationsRepository.findById(formAdvert.getGenerationsId()).orElseThrow(EntityNotFoundException::new));
        advert.setModification(modificationsRepository.findById(formAdvert.getModificationsId()).orElseThrow(EntityNotFoundException::new));
        advertisRepository.save(advert);
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
