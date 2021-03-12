package com.automobil.backend.service;

import com.automobil.backend.dto.CountriesDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.models.Countries;

public interface CountriesService {

    CountriesDto getCountryById(Long Id) throws EntityNotFoundException;

    Countries getCountryByTitle(String title) throws EntityNotFoundException;

    void save(CountriesDto countriesDto);
}
