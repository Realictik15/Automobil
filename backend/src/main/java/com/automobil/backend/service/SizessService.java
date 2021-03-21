package com.automobil.backend.service;

import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;

public interface SizessService {
    SizessDto getById(Long id) throws EntityNotFoundException;

    SizessDto getSize(Long idGen,Long idCarBody) throws EntityNotFoundException;

    void save(SizessDto sizessDto) throws EntityNotFoundException;

    void deleteById(Long id);
}
