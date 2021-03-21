package com.automobil.backend.service;

import com.automobil.backend.dto.*;
import com.automobil.backend.exeption.EntityNotFoundException;

import java.util.List;

public interface GenerationsService {

    List<GenerationsDto> listAll();

    void deleteById(Long id);

    void save(GenerationsDto generationsDto) throws EntityNotFoundException;

    GenerationsDto getById(Long id) throws EntityNotFoundException;

    List<SizessDto> getAllSize(Long id) throws EntityNotFoundException;

    public List<CarbodyDto> getListCarBodyByGenenerationId(Long id) throws EntityNotFoundException;

    List<ModificationsDto> getModificationByGen(Long id) throws EntityNotFoundException;
}
