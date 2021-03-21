package com.automobil.backend.service.serviceImplementation;

import com.automobil.backend.dto.CountriesDto;
import com.automobil.backend.exeption.EntityNotFoundException;
import com.automobil.backend.mapStruct.CountriesMapper;
import com.automobil.backend.models.Countries;
import com.automobil.backend.repository.CountriesRepository;
import com.automobil.backend.repository.MarksRepository;
import com.automobil.backend.service.CountriesService;
import com.automobil.backend.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountiesServiceImpl implements CountriesService {
    private final CountriesRepository countriesRepository;
    private final CountriesMapper countriesMapper;


    @Autowired
    public CountiesServiceImpl(CountriesRepository countriesRepository, CountriesMapper countriesMapper) {
        this.countriesRepository = countriesRepository;
        this.countriesMapper = countriesMapper;
    }

    @Override
    public CountriesDto getCountryById(Long Id) throws EntityNotFoundException {
        return countriesMapper.toCountriesDTO(countriesRepository.findById(Id).
            orElseThrow(() -> new EntityNotFoundException(Id, "Countries")));
    }

    @Override
    public Countries getCountryByTitle(String title) throws EntityNotFoundException {
        return countriesRepository.getCountriesByTitle(title).
            orElseThrow(()->new EntityNotFoundException(title,"Country"));
    }

    @Override
    public void save(CountriesDto countriesDto) {
        countriesRepository.save(countriesMapper.toCountries(countriesDto));
    }
}
