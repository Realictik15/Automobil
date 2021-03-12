package com.automobil.backend.service;

import com.automobil.backend.dto.SizessDto;
import com.automobil.backend.exeption.EntityNotFoundException;

public interface SizessService {
    SizessDto getBId(Long id) throws EntityNotFoundException;

    void seve(SizessDto sizessDto);

    void delete(Long id);
}
