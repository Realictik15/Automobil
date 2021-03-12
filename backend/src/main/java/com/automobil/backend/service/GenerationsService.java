package com.automobil.backend.service;

import com.automobil.backend.dto.EnginesDto;
import com.automobil.backend.dto.GenerationsDto;
import com.automobil.backend.dto.ModificationsDto;
import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;

import java.util.List;

public interface GenerationsService {

    List<GenerationsDto> listAll();

    void deleteById(Long id);

    GenerationsDto getById(Long id) throws EntityNotFoundException;

    List<SizessDto> getAllSize(Long id) throws EntityNotFoundException;

    List<ModificationsDto> getModificationByGen(Long id) throws EntityNotFoundException;
}
