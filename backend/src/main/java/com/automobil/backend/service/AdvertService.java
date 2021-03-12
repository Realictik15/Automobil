package com.automobil.backend.service;

import com.automobil.backend.dto.AdvertismentDto;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Advertisments;

import java.util.List;

public interface AdvertService {

    List<AdvertismentDto> getlistAvalible();

    List<AdvertismentDto> getlistAll();

    List<AdvertismentDto> getReport(Long id) throws EntityNotFoundException;

    AdvertismentDto getById(Long id) throws EntityNotFoundException;

    Advertisments getByIdAdvert(Long id) throws EntityNotFoundException;

    void save(AdvertismentDto advertismentDto) throws EntityNotFoundException;

    void update(AdvertismentDto advertismentDto);

    void deleteById(Long id);

    void userDeleteById(Long id) throws EntityNotFoundException;

    SizessDto getSizeByClass(Long id);
}

