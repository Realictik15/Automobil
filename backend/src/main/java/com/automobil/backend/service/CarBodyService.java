package com.automobil.backend.service;

import com.automobil.backend.dto.CarbodyDto;
import com.automobil.backend.exeption.EntityNotFoundException;

import java.util.List;

public interface CarBodyService {
    CarbodyDto getCarBody(Long id) throws EntityNotFoundException;

    List<CarbodyDto> getListCarBody();

    CarbodyDto getCarBodyByTitle(String title) throws EntityNotFoundException;

}
