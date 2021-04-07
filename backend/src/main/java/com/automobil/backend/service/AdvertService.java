package com.automobil.backend.service;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.FormAdvert;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Advertisments;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface AdvertService {

    List<AdvertismentDto> getlistAvalible();

    List<AdvertismentDto> getlistAll();
    List<AdvertismentDto> getListByClass(Long id);

    List<AdvertismentDto> getReport(Long id) throws EntityNotFoundException;


    AdvertismentDto getById(Long id) throws EntityNotFoundException;
//TODO: ghtndbj
    Advertisments getByIdAdvert(Long id) throws EntityNotFoundException;

    void save(FormAdvert formAdvert, List<String> list) throws EntityNotFoundException, ParseException;

    void update(AdvertismentDto advertismentDto) throws EntityNotFoundException;

    void deleteById(Long id);


    List<String> saveUploadedFiles(List<MultipartFile> files) throws IOException;

    void userDeleteById(Long id) throws EntityNotFoundException;

}

